package com.flower.mine.service;

import com.flower.mine.bean.Account;
import com.flower.mine.bean.Gain;
import com.flower.mine.bean.HashOrder;
import com.flower.mine.repository.AccountRepository;
import com.flower.mine.repository.GainRepository;
import com.flower.mine.repository.HashOrderRepository;
import com.flower.mine.ret.ChartVo;
import com.flower.mine.ret.DataResult;
import com.flower.mine.session.SessionUtil;
import com.flower.mine.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CalculateService {

    private static final Logger log = LoggerFactory.getLogger(CalculateService.class);

    @Autowired
    private GainRepository gainRepository;
    @Autowired
    private HashOrderRepository hashOrderRepository;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * 计算收益定时器
     */
    @Transactional
    @Scheduled(cron = "0 1 0 * * ?")
    public void calculate() {
        calculate(DateUtil.yesterday());
    }

    public void calculate(Date date) {
        log.warn("收益计算定时器开始！");
        /**
         * 1.查询所有生效的订单
         * 2.遍历订单，给用户加btc
         * 3.插入gain表
         */
        List<HashOrder> orders = hashOrderRepository.findAllByStateAndStartTimeLessThanAndEndTimeGreaterThanEqual(HashOrder.Status_Paid, date, date);
        if (orders.isEmpty()) {
            return; //没有订单，结束调用
        }
        BigDecimal e = parameterService.getHashEarning(); //计算系数
        HashMap<String, BigDecimal> orderHashMap = new HashMap<>(orders.size() * 2);
        orders.stream().forEach( order -> {
            BigDecimal v = orderHashMap.get(order.getUsername());
            if ( v != null ) {
                orderHashMap.put(order.getUsername(), v.add(e.multiply(new BigDecimal(order.getHash()))).setScale(10) );
            }
        });

        for (String username : orderHashMap.keySet()) {
            Gain gain = new Gain();
            Gain.GainPK gainPK = new Gain.GainPK();
            gainPK.setDate(date);
            gainPK.setUsername(username);
            gain.setGainPK(gainPK);
            gain.setValue(orderHashMap.get(username));
            gainRepository.save(gain);
            Account account = accountRepository.findById(username).get();
            account.setBalance(account.getBalance().add(gain.getValue()).setScale(10));
        }
        log.warn("收益计算定时器结束！");
    }

    public DataResult<List<ChartVo>> gainChart(Date start, Date end) {
        List<Object[]> list = gainRepository.findAllByUsernameAndDate(SessionUtil.currentUserId(), DateUtil.truncateToDay(start), DateUtil.truncateToDay(end));
        DataResult<List<ChartVo>> result = new DataResult<>();
        List<ChartVo> chartVos = new ArrayList<>(list.size());
        result.setData(chartVos);
        list.stream().forEach(e -> chartVos.add(new ChartVo((String) e[0], (BigDecimal) e[1])));
        return result;
    }
}
