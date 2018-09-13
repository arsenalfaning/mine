package com.flower.mine.controller;

import com.flower.mine.param.*;
import com.flower.mine.ret.LoginResult;
import com.flower.mine.service.AccountService;
import com.flower.mine.service.SmsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SessionController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SmsService smsService;

    @ApiOperation("注册")
    @PostMapping("register")
    public void register(@RequestBody @Valid RegisterParam registerParam) {
        accountService.register(registerParam);
    }

    @ApiOperation("发送短信")
    @PostMapping("sms")
    public void sms(@RequestBody @Valid SendSmsParam sendSmsParam) {
        smsService.sendSms(sendSmsParam);
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public LoginResult login(@RequestBody @Valid LoginParam loginParam) {
        return accountService.login(loginParam);
    }

    @ApiOperation("找回密码")
    @PostMapping("password")
    public void resetPassword(@RequestBody @Valid ResetPasswordParam resetPasswordParam) {
        accountService.resetPassword(resetPasswordParam);
    }

    @ApiOperation("修改密码")
    @PostMapping("api/password")
    public void modifyPassword(@RequestBody @Valid ModifyPasswordParam modifyPasswordParam) {
        accountService.modifyPassword(modifyPasswordParam);
    }
}
