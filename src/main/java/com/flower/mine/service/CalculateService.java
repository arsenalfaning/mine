package com.flower.mine.service;

import com.flower.mine.bean.Account;
import com.flower.mine.bean.Gain;
import com.flower.mine.bean.HashOrder;
import com.flower.mine.repository.AccountRepository;
import com.flower.mine.repository.GainRepository;
import com.flower.mine.repository.HashOrderRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CalculateService {

    @Autowired
    private GainRepository gainRepository;
    @Autowired
    private HashOrderRepository hashOrderRepository;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * 计算收益
     */
    @Transactional
    public void calculate() {
        /**
         * 1.查询所有生效的订单
         * 2.遍历订单，给用户加btc
         * 3.插入gain表
         */
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        Date yesterday = DateUtils.addDays(today, -1);
        List<HashOrderRepository.OrderModel> orders = null;//hashOrderRepository.findOrdersGroupByUsername(HashOrder.Status_Paid, yesterday);
        if (orders.isEmpty()) {
            return; //没有订单，结束调用
        }
        BigDecimal e = parameterService.getHashEarning();
        orders.stream().forEach( order -> {
            Gain gain = new Gain();
            Gain.GainPK gainPK = new Gain.GainPK();
            gainPK.setDate(yesterday);
            gainPK.setUsername(order.getUsername());
            gain.setGainPK(gainPK);
            BigDecimal value = e.multiply(new BigDecimal(order.getHash()));
            value = value.setScale(10);
            gain.setValue(value);
            gainRepository.save(gain);
            Account account = accountRepository.findById(order.getUsername()).get();
            account.setBalance(account.getBalance().add(value).setScale(10));
        });

    }

}
