package com.flower.mine.controller;

import com.flower.mine.bean.Parameter;
import com.flower.mine.param.ParameterPostParam;
import com.flower.mine.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @GetMapping()
    public Page<Parameter> all() {
        return parameterService.all();
    }

    @PostMapping()
    public void parameter(@RequestBody @Valid ParameterPostParam parameterPostParam) {
        parameterService.addOrUpdate(parameterPostParam);
    }

    @DeleteMapping("{name}")
    public void delete(@PathVariable String name) {
        parameterService.delete(name);
    }
}
