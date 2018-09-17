package com.flower.mine.service;

import com.flower.mine.bean.HashOrder;
import com.flower.mine.bean.Hashrate;
import com.flower.mine.exception.BaseRuntimeException;
import com.flower.mine.exception.NotFoundError;
import com.flower.mine.param.HashOrderParam;
import com.flower.mine.repository.HashOrderRepository;
import com.flower.mine.repository.HashrateRepository;
import com.flower.mine.ret.HashOrderResult;
import com.flower.mine.session.SessionUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class HashOrderService {

    @Autowired
    private HashOrderRepository hashOrderRepository;
    @Autowired
    private HashrateRepository hashrateRepository;
    @Autowired
    private ParameterService parameterService;

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
        hashOrder.setCost( parameterService.getHashCost().multiply(new BigDecimal(param.getHash() * hashrate.getPeriod())) );
        hashOrder.setFee( parameterService.getHashFee().multiply(new BigDecimal(param.getHash())) );
        hashOrder.setState(HashOrder.Status_Unpaid);
        hashOrder.setPeriod(hashrate.getPeriod());
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
    public void hashOrderSuccess(Long id) {
        Optional<HashOrder> hashOrderOptional = hashOrderRepository.findById(id);
        if ( !hashOrderOptional.isPresent() ) {
            throw new NotFoundError();
        }
        HashOrder hashOrder = hashOrderOptional.get();
        if ( hashOrder.getState().equals(HashOrder.Status_Unpaid) ) {
            hashOrder.setState(HashOrder.Status_Paid);
            hashOrder.setStartTime( DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH), 1) );
        }
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public Page<HashOrder> page(int page, int size) {
        return hashOrderRepository.findAll(PageRequest.of(page, size));
    }
}
