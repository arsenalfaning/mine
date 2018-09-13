package com.flower.mine.service;

import com.flower.mine.bean.Account;
import com.flower.mine.bean.Sms;
import com.flower.mine.exception.*;
import com.flower.mine.param.LoginParam;
import com.flower.mine.param.ModifyPasswordParam;
import com.flower.mine.param.RegisterParam;
import com.flower.mine.param.ResetPasswordParam;
import com.flower.mine.repository.AccountRepository;
import com.flower.mine.repository.SmsRepository;
import com.flower.mine.ret.LoginResult;
import com.flower.mine.session.SessionInfo;
import com.flower.mine.session.SessionUtil;
import com.flower.mine.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SmsRepository smsRepository;

    /**
     * 账号注册
     * @param param
     */
    @Transactional
    public void register(@NotNull RegisterParam param) {
        /**
         * 1.判断sms和手机号是否正确，手机号是否重复，正确->2，错误抛异常
         * 2.插入新账号
         * 3.修改短信码状态为已使用
         */
        if ( accountRepository.existsById(param.getMobile()) ) { //手机号码已注册
            throw new AccountDuplicateException();
        }
        Sms sms = smsRepository.findTopByMobileAndCodeAndConsumedOrderByIdDesc(param.getMobile(), param.getSms(), false);
        if ( sms == null ) {
            throw new SmsError();
        }
        Account account = new Account();
        account.setInvitedId(param.getInvitedId());
        account.setMobile(param.getMobile());
        account.setSalt(UUID.randomUUID().toString().replaceAll("-", ""));
        account.setPassword(PasswordUtil.hashPassword(param.getPassword(), account.getSalt()));
        accountRepository.save(account);
        sms.setConsumed(true);
    }

    /**
     * 登录
     * @param loginParam
     * @return
     */
    public LoginResult login(LoginParam loginParam) {
        Optional<Account> accounts = accountRepository.findById(loginParam.getMobile());
        if ( !accounts.isPresent() ) {
            throw new LoginError();
        }
        if ( !PasswordUtil.checkPassword(loginParam.getPassword(), accounts.get().getSalt(), accounts.get().getPassword()) ) {
            throw new LoginError();
        }
        LoginResult loginResult = new LoginResult();
        loginResult.setMobile(loginParam.getMobile());
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setMobile(loginParam.getMobile());
        loginResult.setToken(SessionUtil.token(sessionInfo));
        return loginResult;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public Page<Account> page(int page, int size) {
        Page<Account> accounts = accountRepository.findAll(PageRequest.of(page, size));
        return accounts;
    }

    /**
     * 重置密码
     * @param param
     */
    @Transactional
    public void resetPassword(ResetPasswordParam param) {
        /**
         * 1.判断账号是否存在
         * 2.判断短信验证码是否正确
         * 3.修改密码并消费短信
         */
        if ( !accountRepository.existsById(param.getMobile()) ) { //手机号码不存在
            throw new ResetPasswordError();
        }
        Sms sms = smsRepository.findTopByMobileAndCodeAndConsumedOrderByIdDesc(param.getMobile(), param.getSms(), false);
        if ( sms == null ) {
            throw new SmsError();
        }
        Account account = accountRepository.findById(param.getMobile()).get();
        account.setPassword(PasswordUtil.hashPassword(param.getPassword(), account.getSalt()));
        sms.setConsumed(true);
    }

    /**
     * 修改密码
     * @param param
     */
    public void modifyPassword(ModifyPasswordParam param) {
        /**
         * 1.判断旧密码是否正确
         * 2.修改密码
         */
        String mobile = SessionUtil.currentUserId();
        Account account = accountRepository.findById(mobile).get();
        if ( !PasswordUtil.checkPassword(param.getPassword(), account.getSalt(), account.getPassword()) ) {
            throw new PasswordError();
        }
        account.setPassword(PasswordUtil.hashPassword(param.getNewPassword(), account.getSalt()));
    }
}
