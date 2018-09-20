package com.flower.mine.repository;

import com.flower.mine.bean.CalculateHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface CalculateHistoryRepository extends PagingAndSortingRepository<CalculateHistory, Date> {
}
