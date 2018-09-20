package com.flower.mine.repository;

import com.flower.mine.bean.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address, String> {
}
