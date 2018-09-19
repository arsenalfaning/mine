package com.flower.mine.controller;

import com.flower.mine.ret.DataResult;
import com.flower.mine.ret.StatVo;
import com.flower.mine.service.StatService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatController {

    @Autowired
    private StatService statService;

    @ApiOperation("收入统计-管理员")
    @GetMapping("api/stat/income")
    @Role
    public DataResult<List<StatVo>> chargeStat(@RequestParam int type, @RequestParam long start, @RequestParam long end) {
        return statService.chargeStat(type, start, end);
    }

    @ApiOperation("支出统计-管理员")
    @GetMapping("api/stat/expense")
    @Role
    public DataResult<List<StatVo>> expenseStat(@RequestParam int type, @RequestParam long start, @RequestParam long end) {
        return statService.expenseStat(type, start, end);
    }
}
