package com.flower.mine.controller;

import com.flower.mine.bean.SysUser;
import com.flower.mine.param.LoginParam;
import com.flower.mine.param.SysUserParam;
import com.flower.mine.ret.LoginResult;
import com.flower.mine.service.SysUserService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("管理员登录")
    @PostMapping("admin/login")
    public LoginResult login(@RequestBody @Valid LoginParam loginParam) {
        return sysUserService.login(loginParam);
    }

    @ApiOperation("管理员列表")
    @Role
    @GetMapping("api/admin/users")
    public Page<SysUser> page(@RequestParam int page, @RequestParam int size) {
        return sysUserService.page(page, size);
    }

    @ApiOperation("管理员新增/修改")
    @Role
    @PostMapping("api/admin/users")
    public void addOrUpdate(SysUserParam sysUserParam) {
        sysUserService.addOrUpdate(sysUserParam);
    }

    @ApiOperation("管理员删除")
    @Role
    @DeleteMapping("api/admin/users/{username}")
    public void delete(@PathVariable("username") String username) {
        sysUserService.delete(username);
    }
}
