package com.flower.mine.service;

import com.flower.mine.bean.Charge;
import com.flower.mine.repository.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChargeService {

    @Autowired
    private ChargeRepository chargeRepository;

    public Page<Charge> page(int page, int size) {
        return chargeRepository.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "id"));
    }
}
