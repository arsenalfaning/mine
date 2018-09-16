package com.flower.mine.service;

import com.flower.mine.bean.Hashrate;
import com.flower.mine.param.HashratePostParam;
import com.flower.mine.param.HashratePutParam;
import com.flower.mine.repository.HashrateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HashrateService {

    @Autowired
    private HashrateRepository hashrateRepository;

    /**
     * 查询商品列表
     * @return
     */
    public Page<Hashrate> mainPage() {
        return hashrateRepository.findAllByDeleted(false, PageRequest.of(0, Integer.MAX_VALUE));
    }

    /**
     * 新增商品
     * @param param
     */
    public void add(HashratePostParam param) {
        Hashrate hashrate = new Hashrate();
        hashrate.setDeleted(false);
        hashrate.setMax(param.getMax());
        hashrate.setMin(param.getMin());
        hashrate.setPeriod(param.getPeriod());
        hashrate.setPrice(param.getPrice());
        hashrate.setTotal(param.getTotal());
        hashrateRepository.save(hashrate);
    }

    /**
     * 修改商品
     * @param param
     */
    public void update(HashratePutParam param) {
        Hashrate hashrate = new Hashrate();
        hashrate.setDeleted(false);
        hashrate.setMax(param.getMax());
        hashrate.setMin(param.getMin());
        hashrate.setPeriod(param.getPeriod());
        hashrate.setPrice(param.getPrice());
        hashrate.setTotal(param.getTotal());
        hashrate.setId(param.getId());
        hashrate.setDeleted(param.getDeleted());
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
        }
    }
}
