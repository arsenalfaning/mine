package com.flower.mine.repository;

import com.flower.mine.bean.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, String> {

}
