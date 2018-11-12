package com.flower.mine.repository;

import com.flower.mine.bean.HashOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface HashOrderRepository extends PagingAndSortingRepository<HashOrder, Long> {

    Page<HashOrder> findAllByState(Integer state, Pageable pageable);

    /**
     * 查询所有符合条件的订单
     * @param state
     * @param startTime
     * @param endTime
     * @return
     */
    List<HashOrder> findAllByStateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Integer state, Date startTime, Date endTime);

    /**
     * 我的算力（不计开始时间）
     * @param state
     * @param username
     * @param endTime
     * @return
     */
    List<HashOrder> findAllByStateAndUsernameAndEndTimeGreaterThanEqual(Integer state, String username, Date endTime);

    /**
     * 我的真正算力
     * @param state
     * @param username
     * @param startTime
     * @param endTime
     * @return
     */
    List<HashOrder> findAllByStateAndUsernameAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Integer state, String username, Date startTime, Date endTime);
}
