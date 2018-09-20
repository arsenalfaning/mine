package com.flower.mine.repository;

import com.flower.mine.bean.WithdrawApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WithdrawApplyRepository extends PagingAndSortingRepository<WithdrawApply, Long> {
    Page<WithdrawApply>  findAllByState(Integer state, Pageable pageable);
}
