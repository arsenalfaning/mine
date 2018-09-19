package com.flower.mine.repository;

import com.flower.mine.bean.Charge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ChargeRepository extends PagingAndSortingRepository<Charge, Long> {
    @Query(value = "SELECT date_format(created_time, :format) as date, sum(value) as value FROM charge where created_time >= :start and created_time <= :end group by date order by date", nativeQuery = true)
    List<Object[]> chargeStat(@Param("format") String format, @Param("start") Date start, @Param("end") Date end);
}
