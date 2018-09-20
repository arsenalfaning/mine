package com.flower.mine.controller;

import com.flower.mine.bean.WithdrawApply;
import com.flower.mine.param.WithdrawApplyParam;
import com.flower.mine.service.WithdrawService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("提现列表-管理员 state:0-未通过 1-已通过")
    @GetMapping("api/withdraw-apply")
    @Role
    public Page<WithdrawApply> applyPage(int page,int size, int state) {
        return withdrawService.applyPage(page, size, state);
    }
}
