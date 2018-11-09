package com.flower.mine.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.flower.mine.bean.Sms;
import com.flower.mine.exception.SmsSendError;
import com.flower.mine.param.SendSmsParam;
import com.flower.mine.repository.SmsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private SmsRepository smsRepository;
    @Autowired
    private AliyunService aliyunService;

    /**
     * 发送短信
     * @param sendSmsParam
     */
    public void sendSms(SendSmsParam sendSmsParam) throws ClientException {
        Sms sms = new Sms();
        sms.setConsumed(false);
        sms.setCode(RandomStringUtils.randomNumeric(6));
        sms.setMobile(sendSmsParam.getMobile());
        smsRepository.save(sms);
        SendSmsResponse response = aliyunService.sendSms(sms.getMobile(), sms.getCode());
        if ( !"OK".equals(response.getCode()) ) {
            log.error("短信发送失败，错误码：{}，错误消息：{}", response.getCode(), response.getMessage());
            throw new SmsSendError();
        }
    }
}
