package com.flower.mine.controller;

import com.flower.mine.bean.Parameter;
import com.flower.mine.param.ParameterPostParam;
import com.flower.mine.service.ParameterService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @ApiOperation("参数列表")
    @GetMapping()
    public Page<Parameter> all() {
        return parameterService.all();
    }

    @ApiOperation("参数新增/修改-管理员")
    @Role
    @PostMapping()
    public void parameter(@RequestBody @Valid ParameterPostParam parameterPostParam) {
        parameterService.addOrUpdate(parameterPostParam);
    }

    @ApiOperation("参数删除-管理员")
    @Role
    @DeleteMapping("{name}")
    public void delete(@PathVariable String name) {
        parameterService.delete(name);
    }
}
