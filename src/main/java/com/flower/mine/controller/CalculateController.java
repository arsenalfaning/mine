package com.flower.mine.controller;

import com.flower.mine.service.CalculateService;
import com.flower.mine.util.DateUtil;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @ApiOperation("计算某天的收益数据-手动触发")
    @PostMapping("api/calculate")
    @Role
    public void calculate(@RequestParam long date) {
        calculateService.calculate(DateUtil.truncateToDay(new Date(date)));
    }
}
