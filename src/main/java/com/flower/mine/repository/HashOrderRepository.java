package com.flower.mine.repository;

import com.flower.mine.bean.HashOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface HashOrderRepository extends PagingAndSortingRepository<HashOrder, Long> {

    Page<HashOrder> findAllByState(Integer state, Pageable pageable);

    List<HashOrder> findAllByStateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Integer state, Date startTime, Date endTime);

    List<HashOrder> findAllByStateAndUsernameAndEndTimeGreaterThanEqual(Integer state, String username, Date endTime);
}
