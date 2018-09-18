package com.flower.mine.controller;

import com.flower.mine.param.WithdrawApplyParam;
import com.flower.mine.service.WithdrawService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;

    @ApiOperation("申请提现")
    @PostMapping("api/withdraw-apply")
    public void apply(@RequestBody @Valid WithdrawApplyParam withdrawApplyParam) {
        withdrawService.apply(withdrawApplyParam);
    }

    @ApiOperation("申请提现批准-管理员")
    @PostMapping("api/withdraw-apply/{id}/succeed")
    @Role
    public void applySucceed(@PathVariable Long id) {
        withdrawService.applySucceed(id);
    }
}
