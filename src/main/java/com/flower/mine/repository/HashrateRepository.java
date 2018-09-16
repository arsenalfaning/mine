package com.flower.mine.repository;

import com.flower.mine.bean.Hashrate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HashrateRepository extends PagingAndSortingRepository<Hashrate, Long> {
    Page<Hashrate> findAllByDeleted(Boolean deleted, Pageable pageable);
}
