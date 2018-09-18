package com.flower.mine.repository;

import com.flower.mine.bean.Charge;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChargeRepository extends PagingAndSortingRepository<Charge, Long> {
}
