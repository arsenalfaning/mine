package com.flower.mine.controller;

import com.flower.mine.bean.Charge;
import com.flower.mine.service.ChargeService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @ApiOperation("充值记录列表-管理员")
    @GetMapping("api/charges")
    @Role
    public Page<Charge> page(int page, int size) {
        return chargeService.page(page, size);
    }
}
