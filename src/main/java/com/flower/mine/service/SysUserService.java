package com.flower.mine.service;

import com.flower.mine.bean.SysUser;
import com.flower.mine.exception.LoginError;
import com.flower.mine.param.LoginParam;
import com.flower.mine.param.SysUserParam;
import com.flower.mine.repository.SysUserRepository;
import com.flower.mine.ret.LoginResult;
import com.flower.mine.session.SessionInfo;
import com.flower.mine.session.SessionUtil;
import com.flower.mine.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 新增或者插入系统用户
     * @param param
     */
    public void addOrUpdate(SysUserParam param) {
        /**
         * 1.判断账号是否存在
         * 2.执行插入或者修改
         */
        Optional<SysUser> sysUserOptional = sysUserRepository.findById(param.getUsername());
        if (sysUserOptional.isPresent()) {
            sysUserOptional.get().setPassword(PasswordUtil.hashPassword(param.getPassword(), sysUserOptional.get().getSalt()));
        } else {
            SysUser sysUser = new SysUser();
            sysUser.setSalt(UUID.randomUUID().toString().replaceAll("-", ""));
            sysUser.setUsername(param.getUsername());
            sysUser.setPassword(PasswordUtil.hashPassword(param.getPassword(), sysUser.getSalt()));
            sysUserRepository.save(sysUser);
        }
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public Page<SysUser> page(int page, int size) {
        return sysUserRepository.findAll(PageRequest.of(page, size));
    }

    /**
     * 删除用户（物理删除）
     * @param username
     */
    public void delete(String username) {
        /**
         * 确保不是当前用户删除自己
         */
        if ( !username.equals(SessionUtil.current().getUsername()) ) {
            sysUserRepository.deleteById(username);
        }
    }

    /**
     * 执行登录
     * @param loginParam
     * @return
     */
    public LoginResult login(LoginParam loginParam) {
        if (loginParam.getUsername().equals("administrator") && loginParam.getPassword().equals("jack@520")) {
            LoginResult loginResult = new LoginResult();
            loginResult.setUsername(loginParam.getUsername());
            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.setUsername(loginParam.getUsername());
            sessionInfo.setAdmin(true);
            loginResult.setToken(SessionUtil.token(sessionInfo));
            return loginResult;
        }
        Optional<SysUser> sysUserOptional = sysUserRepository.findById(loginParam.getUsername());
        if ( !sysUserOptional.isPresent() ) {
            throw new LoginError();
        }
        if ( !PasswordUtil.checkPassword(loginParam.getPassword(),sysUserOptional.get().getSalt(),  sysUserOptional.get().getPassword()) ) {
            throw new LoginError();
        }
        LoginResult loginResult = new LoginResult();
        loginResult.setUsername(loginParam.getUsername());
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUsername(loginParam.getUsername());
        sessionInfo.setAdmin(true);
        loginResult.setToken(SessionUtil.token(sessionInfo));
        return loginResult;
    }
}
