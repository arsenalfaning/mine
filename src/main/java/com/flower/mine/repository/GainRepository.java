package com.flower.mine.repository;

import com.flower.mine.bean.Gain;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GainRepository extends PagingAndSortingRepository<Gain, Gain.GainPK> {
}
