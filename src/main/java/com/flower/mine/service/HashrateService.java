package com.flower.mine.service;

import com.flower.mine.bean.Hashrate;
import com.flower.mine.exception.NotFoundError;
import com.flower.mine.param.HashratePostParam;
import com.flower.mine.param.HashratePutParam;
import com.flower.mine.repository.HashrateRepository;
import com.flower.mine.ret.HashrateVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class HashrateService {

    @Autowired
    private HashrateRepository hashrateRepository;
    @Autowired
    private ParameterService parameterService;

    /**
     * 查询商品列表
     * @return
     */
    public HashrateVo mainPage() {
        HashrateVo hashrateVo = new HashrateVo();
        hashrateVo.setData(hashrateRepository.findAllByDeleted(false, PageRequest.of(0, Integer.MAX_VALUE)));
        Map<String, Object> params = new HashMap<>();
//        params.put("cost", parameterService.getHashCost());
        params.put("fee", parameterService.getHashFee());
        params.put("earning",parameterService.getHashEarning());
        hashrateVo.setParams(params);
        return hashrateVo;
    }

    /**
     * 新增商品
     * @param param
     */
    public void add(HashratePostParam param) {
        Hashrate hashrate = new Hashrate();
        hashrate.setDeleted(false);
        hashrate.setMax(param.getTotal());
        hashrate.setMin(param.getMin());
        hashrate.setPeriod(param.getPeriod());
        hashrate.setPrice(param.getPrice());
        hashrate.setTotal(param.getTotal());
//        hashrate.setStartTime(param.getStartTime());
//        hashrate.setEndTime(DateUtils.addYears(param.getStartTime(), param.getPeriod().intValue()));
        hashrate.setBalance(param.getTotal());
        hashrate.setElectricityFeeType(param.getElectricityFeeType());
        if (param.getElectricityFeeType() == Hashrate.Electricity_Fee_Type_Has_Fee) {
            hashrate.setElectricityFee(param.getElectricityFee());
            if (hashrate.getElectricityFee() == null) {
                hashrate.setElectricityFee(BigDecimal.ZERO);
            }
        }
        hashrateRepository.save(hashrate);
    }

    /**
     * 修改商品
     * @param param
     */
    public void update(HashratePutParam param) {
        Optional<Hashrate> hashrateOptional = hashrateRepository.findById(param.getId());
        if ( !hashrateOptional.isPresent() ) {
            throw new NotFoundError();
        }
        Hashrate hashrate = hashrateOptional.get();
        hashrate.setMax(param.getMax());
        hashrate.setMin(param.getMin());
        hashrate.setPeriod(param.getPeriod());
        hashrate.setPrice(param.getPrice());
        hashrate.setTotal(param.getTotal());
        hashrate.setDeleted(param.getDeleted());
//        hashrate.setStartTime(param.getStartTime());
//        hashrate.setEndTime(DateUtils.addYears(param.getStartTime(), param.getPeriod().intValue()));
        hashrateRepository.save(hashrate);
    }

    /**
     * 删除商品
     * @param id 商品id
     */
    public void delete(Long id) {
        Optional<Hashrate> optionalHashrate = hashrateRepository.findById(id);
        if (optionalHashrate.isPresent()) {
            optionalHashrate.get().setDeleted(true);
            hashrateRepository.save(optionalHashrate.get());
        }
    }
}
