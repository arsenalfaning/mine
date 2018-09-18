package com.flower.mine.repository;

import com.flower.mine.bean.HashOrder;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface HashOrderRepository extends PagingAndSortingRepository<HashOrder, Long> {

    List<HashOrder> findAllByStateAndStartTimeLessThanAndEndTimeGreaterThanEqual(Integer state, Date startTime, Date endTime);

    List<HashOrder> findAllByStateAndUsernameAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Integer state, String username, Date startTime, Date endTime);
}
