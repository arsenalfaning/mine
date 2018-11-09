package com.flower.mine.controller;

import com.aliyuncs.exceptions.ClientException;
import com.flower.mine.param.*;
import com.flower.mine.ret.AccountState;
import com.flower.mine.ret.ChartVo;
import com.flower.mine.ret.DataResult;
import com.flower.mine.ret.LoginResult;
import com.flower.mine.service.AccountService;
import com.flower.mine.service.CalculateService;
import com.flower.mine.service.SmsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class SessionController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private CalculateService calculateService;

    @ApiOperation("注册")
    @PostMapping("register")
    public void register(@RequestBody @Valid RegisterParam registerParam) {
        accountService.register(registerParam);
    }

    @ApiOperation("发送短信")
    @PostMapping("sms")
    public void sms(@RequestBody @Valid SendSmsParam sendSmsParam) throws ClientException {
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

    @ApiOperation("状态总览")
    @GetMapping("api/state")
    public AccountState accountState() {
        return accountService.accountState();
    }

    @ApiOperation("收益列表")
    @GetMapping("api/gain-chart")
    public DataResult<List<ChartVo>> gainChart(@RequestParam Long start, @RequestParam Long end) {
        return calculateService.gainChart( new Date(start), new Date(end) );
    }
}
