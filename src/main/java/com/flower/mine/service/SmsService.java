package com.flower.mine.service;

import com.flower.mine.bean.Sms;
import com.flower.mine.param.SendSmsParam;
import com.flower.mine.repository.SmsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    private SmsRepository smsRepository;

    /**
     * 发送短信
     * @param sendSmsParam
     */
    public void sendSms(SendSmsParam sendSmsParam) {
        Sms sms = new Sms();
        sms.setConsumed(false);
//        sms.setCode(RandomStringUtils.randomNumeric(6));
        sms.setCode("666666");
        sms.setMobile(sendSmsParam.getMobile());
        smsRepository.save(sms);
    }
}
