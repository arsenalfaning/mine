package com.flower.mine.service;

import com.flower.mine.bean.*;
import com.flower.mine.repository.*;
import com.flower.mine.ret.ChartVo;
import com.flower.mine.ret.DataResult;
import com.flower.mine.session.SessionUtil;
import com.flower.mine.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    private CalculateHistoryRepository calculateHistoryRepository;
    @Autowired
    private HashrateRepository hashrateRepository;

    /**
     * 计算收益定时器
     */
    @Transactional
    @Scheduled(cron = "0 15 0 * * ?")
    public void calculate() {
        calculate(DateUtil.yesterday());
    }

    @Transactional
    public void calculate(Date date) {
        log.warn("收益计算定时器开始！");
        /**
         * 1.查询所有生效的订单
         * 2.遍历订单，给用户加btc
         * 3.插入gain表
         */
        if (date.after(DateUtil.yesterday())) {
            log.warn("统计日期不能大于昨天：{}，收益计算定时器结束！", date);
            return;
        }
        if (calculateHistoryRepository.existsById(date)) {
            log.warn("该日已计算过{}，收益计算定时器结束！", date);
            return;
        }
        List<HashOrder> orders = hashOrderRepository.findAllByStateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(HashOrder.Status_Paid, date, date);
        if (orders.isEmpty()) {
            log.warn("没有订单，收益计算定时器结束！");
            return; //没有订单，结束调用
        }
        BigDecimal e = parameterService.getHashEarning(); //计算系数
        BigDecimal fee = parameterService.getHashFee();
        HashMap<String, BigDecimal> orderHashMap = new HashMap<>(orders.size() * 2);
        HashMap<Long, Hashrate> hashrateHashMap = new HashMap<>();
        orders.stream().forEach( order -> {
            BigDecimal v = orderHashMap.get(order.getUsername());
            if (v == null) {
                v = new BigDecimal(0);
            }
            v = v.add( e.subtract(fee).multiply(new BigDecimal(order.getHash())) );
            if ( !hashrateHashMap.containsKey(order.getRateId()) ) {
                hashrateHashMap.put(order.getRateId(), hashrateRepository.findById(order.getRateId()).get());
            }
            Hashrate hashrate = hashrateHashMap.get(order.getRateId());
            if (hashrate.getElectricityFeeType() == Hashrate.Electricity_Fee_Type_Has_Fee) {
                v = v.subtract( hashrate.getElectricityFee().multiply( new BigDecimal(order.getHash())) );
            }
            orderHashMap.put(order.getUsername(),  v.setScale(10));
        });

        for (String username : orderHashMap.keySet()) {
            Gain gain = new Gain();
            Gain.GainPK gainPK = new Gain.GainPK();
            gainPK.setDate(date);
            gainPK.setUsername(username);
            gainPK.setType(Gain.Type_Mine);
            gain.setGainPK(gainPK);
            gain.setValue(orderHashMap.get(username));
            gainRepository.save(gain);
            Account account = accountRepository.findById(username).get();
            if (account.getBalance() == null) {
                account.setBalance(new BigDecimal(0));
            }
            account.setBalance(account.getBalance().add(gain.getValue()).setScale(10));
            accountRepository.save(account);
        }
        CalculateHistory calculateHistory = new CalculateHistory();
        calculateHistory.setDate(date);
        calculateHistoryRepository.save(calculateHistory);
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
