package com.flower.mine.repository;

import com.flower.mine.bean.Gain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface GainRepository extends PagingAndSortingRepository<Gain, Gain.GainPK> {
    @Query(value = "SELECT DATE_FORMAT(date, '%Y-%m-%d'), SUM(value) FROM gain WHERE username = :username AND date >= :start  AND date <= :end GROUP BY date", nativeQuery = true)
    List<Object[]> findAllByUsernameAndDate(@Param("username") String username, @Param("start") Date start, @Param("end") Date end);
}
