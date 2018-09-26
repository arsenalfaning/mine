package com.flower.mine.service;

import com.flower.mine.bean.Parameter;
import com.flower.mine.param.ParameterPostParam;
import com.flower.mine.repository.ParameterRepository;
import com.flower.mine.util.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Lazy(false)
public class ParameterService {

//    @Value("${app.parameter.cost}")
//    private BigDecimal hashCost;
    @Value("${app.parameter.fee}")
    private BigDecimal hashFee;
    @Value("${app.parameter.earning}")
    private BigDecimal hashEarning;
    @Value("${app.parameter.admin-address}")
    private String adminAddress;
    @Value("${app.parameter.min-withdraw}")
    private BigDecimal minWithdraw;
    @Value("${app.parameter.recommend}")
    private BigDecimal recommend;

    @Autowired
    private ParameterRepository parameterRepository;

    public Page<Parameter> all() {
        return parameterRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE));
    }

    public void addOrUpdate(ParameterPostParam param) {
        save(param.getName(), param.getValue());
    }

    public void delete(String name) {
        parameterRepository.deleteById(name);
    }

    public Optional<Parameter> queryByName(String name) {
        return parameterRepository.findById(name);
    }

    @PostConstruct
    public void init() {
//        if ( !parameterRepository.existsById(ConstUtil.Parameter_Hash_Cost) ) {
//            save(ConstUtil.Parameter_Hash_Cost, hashCost.toString());
//        }
        if ( !parameterRepository.existsById(ConstUtil.Parameter_Hash_Fee) ) {
            save(ConstUtil.Parameter_Hash_Fee, hashFee.toString());
        }
        if ( !parameterRepository.existsById(ConstUtil.Parameter_Hash_Earning) ) {
            save(ConstUtil.Parameter_Hash_Earning, hashEarning.toString());
        }
        if ( !parameterRepository.existsById(ConstUtil.Parameter_Admin_Address) ) {
            save(ConstUtil.Parameter_Admin_Address, adminAddress);
        }
        if ( !parameterRepository.existsById(ConstUtil.Parameter_Withdraw_Min) ) {
            save(ConstUtil.Parameter_Withdraw_Min, minWithdraw.toString());
        }
        if ( !parameterRepository.existsById(ConstUtil.Parameter_Recommend) ) {
            save(ConstUtil.Parameter_Recommend, recommend.toString());
        }
    }

//    public BigDecimal getHashCost() {
//        return new BigDecimal(parameterRepository.findById(ConstUtil.Parameter_Hash_Cost).get().getValue());
//    }


    public BigDecimal getHashFee() {
        return new BigDecimal(parameterRepository.findById(ConstUtil.Parameter_Hash_Fee).get().getValue());
    }

    public BigDecimal getHashEarning() {
        return new BigDecimal(parameterRepository.findById(ConstUtil.Parameter_Hash_Earning).get().getValue());
    }

    public String getAdminAddress() {
        return parameterRepository.findById(ConstUtil.Parameter_Admin_Address).get().getValue();
    }

    public BigDecimal getMinWithdraw() {
        return new BigDecimal(parameterRepository.findById(ConstUtil.Parameter_Withdraw_Min).get().getValue());
    }

    public BigDecimal getRecommend() {
        return new BigDecimal(parameterRepository.findById(ConstUtil.Parameter_Recommend).get().getValue());
    }

    private void save(String name, String value) {
        Parameter parameter = new Parameter();
        parameter.setName(name);
        parameter.setValue(value);
        parameterRepository.save(parameter);
    }
}
