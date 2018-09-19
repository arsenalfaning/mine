package com.flower.mine.repository;

import com.flower.mine.bean.Withdraw;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WithdrawRepository extends PagingAndSortingRepository<Withdraw, Long> {
    @Query(value = "SELECT date_format(created_time, :format) as date, sum(value) as value FROM withdraw where created_time >= :start and created_time <= :end group by date order by date", nativeQuery = true)
    List<Object[]> withdrawStat(@Param("format") String format, @Param("start") Date start, @Param("end") Date end);
}
