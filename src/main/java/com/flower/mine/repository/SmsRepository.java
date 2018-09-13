package com.flower.mine.repository;

import com.flower.mine.bean.Sms;
import org.springframework.data.repository.CrudRepository;

public interface SmsRepository extends CrudRepository<Sms, Long> {
    Sms findTopByMobileAndCodeAndConsumedOrderByIdDesc(String mobile, String code, Boolean consumed);
}
