package com.flower.mine.service;

import com.flower.mine.bean.*;
import com.flower.mine.exception.BaseRuntimeException;
import com.flower.mine.exception.HashNotEnoughError;
import com.flower.mine.exception.NotFoundError;
import com.flower.mine.param.HashOrderParam;
import com.flower.mine.repository.*;
import com.flower.mine.ret.HashOrderResult;
import com.flower.mine.session.SessionUtil;
import com.flower.mine.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HashOrderService {

    @Autowired
    private HashOrderRepository hashOrderRepository;
    @Autowired
    private HashrateRepository hashrateRepository;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private ChargeRepository chargeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private GainRepository gainRepository;

    /**
     * 下单
     * @param param
     */
    public HashOrderResult hashOrder(HashOrderParam param) {
        Optional<Hashrate> hashrateOptional = hashrateRepository.findById(param.getHashRateId());
        if ( !hashrateOptional.isPresent() ) {
            throw new NotFoundError();
        }
        Hashrate hashrate = hashrateOptional.get();
        if (param.getHash() < hashrate.getMin() || param.getHash() > hashrate.getMax()) {
            throw new BaseRuntimeException();
        }
        HashOrder hashOrder = new HashOrder();
        hashOrder.setUsername(SessionUtil.currentUserId());
        hashOrder.setHash(param.getHash());
        hashOrder.setCost( hashrate.getPrice().multiply( new BigDecimal( param.getHash() ) ).setScale(10) );
        hashOrder.setFee( parameterService.getHashFee().multiply(new BigDecimal(param.getHash())).setScale(10) );
        hashOrder.setState(HashOrder.Status_Unpaid);
        hashOrder.setPeriod(hashrate.getPeriod());
        hashOrder.setRateId(param.getHashRateId());
        hashOrderRepository.save(hashOrder);
        HashOrderResult result = new HashOrderResult();
        result.setAddress(parameterService.getAdminAddress());
        result.setCost(hashOrder.getCost());
        return result;
    }

    /**
     * 确认订单生效
     * @param id 订单id
     */
    @Transactional
    public void hashOrderSuccess(Long id) {
        Optional<HashOrder> hashOrderOptional = hashOrderRepository.findById(id);
        if ( !hashOrderOptional.isPresent() ) {
            throw new NotFoundError();
        }
        HashOrder hashOrder = hashOrderOptional.get();
        if ( hashOrder.getState().equals(HashOrder.Status_Unpaid) ) {
            Optional<Hashrate> hashrateOptional = hashrateRepository.findById(hashOrder.getRateId());
            Hashrate hashrate = hashrateOptional.get();
            if (hashrate.getBalance() < hashOrder.getHash()) {
                throw new HashNotEnoughError();
            }
            hashOrder.setState(HashOrder.Status_Paid);
            hashOrder.setStartTime( DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH), 1) );
            hashOrder.setEndTime(DateUtils.addYears(hashOrder.getStartTime(), hashrate.getPeriod()));
            hashrate.setBalance(hashrate.getBalance() - hashOrder.getHash());
            hashOrderRepository.save(hashOrder);
            Charge charge = new Charge();
            charge.setUsername(hashOrder.getUsername());
            charge.setValue(hashOrder.getCost());
            chargeRepository.save(charge);
            Account account = accountRepository.findById(hashOrder.getUsername()).get();
            if ( StringUtils.isNoneBlank(account.getInvitedId()) ) {
                Account invitedAccount = accountRepository.findById(account.getInvitedId()).get();
                if (invitedAccount.getBalance() == null) {
                    invitedAccount.setBalance(BigDecimal.ZERO);
                }
                BigDecimal v = parameterService.getRecommend().multiply(new BigDecimal(hashOrder.getHash())).setScale(10);
                invitedAccount.setBalance( invitedAccount.getBalance().add(v).setScale(10) );
                accountRepository.save(invitedAccount);
                Gain gain = new Gain();
                Gain.GainPK gainPK = new Gain.GainPK();
                gainPK.setDate(DateUtil.today());
                gainPK.setUsername(invitedAccount.getMobile());
                gainPK.setType(Gain.Type_Recommend);
                gain.setGainPK(gainPK);
                gain.setValue(v);
                gainRepository.save(gain);
            }
        }
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public Page<HashOrder> page(int page, int size) {
        return hashOrderRepository.findAllByState(HashOrder.Status_Unpaid, PageRequest.of(page, size, Sort.Direction.DESC, "id"));
    }

    /**
     * 某用户当前算力
     * @param username
     * @return
     */
    public Integer  currentHash(String username) {
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        List<HashOrder> orders = hashOrderRepository.findAllByStateAndUsernameAndEndTimeGreaterThanEqual(HashOrder.Status_Paid, username, today);
        return orders.stream().mapToInt(HashOrder::getHash).sum();
    }
}
