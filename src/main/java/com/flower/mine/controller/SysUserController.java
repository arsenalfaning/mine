package com.flower.mine.controller;

import com.flower.mine.bean.SysUser;
import com.flower.mine.param.LoginParam;
import com.flower.mine.param.SysUserParam;
import com.flower.mine.ret.LoginResult;
import com.flower.mine.service.SysUserService;
import com.flower.mine.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("admin/login")
    public LoginResult login(@RequestBody @Valid LoginParam loginParam) {
        return sysUserService.login(loginParam);
    }

    @Role
    @GetMapping("admin/users")
    public Page<SysUser> page(@RequestParam int page, @RequestParam int size) {
        return sysUserService.page(page, size);
    }

    @Role
    @PostMapping("admin/users")
    public void addOrUpdate(SysUserParam sysUserParam) {
        sysUserService.addOrUpdate(sysUserParam);
    }

    @Role
    @DeleteMapping("admin/users/{username}")
    public void delete(@PathVariable("username") String username) {
        sysUserService.delete(username);
    }
}
