package com.flower.mine.controller;

import com.flower.mine.bean.Hashrate;
import com.flower.mine.param.HashratePostParam;
import com.flower.mine.param.HashratePutParam;
import com.flower.mine.service.HashrateService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/hashrates")
public class HashrateController {

    @Autowired
    private HashrateService hashrateService;

    @ApiOperation("所有算力产品")
    @GetMapping
    public Page<Hashrate> all() {
        return hashrateService.mainPage();
    }

    @ApiOperation("新增算力产品-管理员")
    @PostMapping
    @Role
    public void add(@RequestBody @Valid HashratePostParam hashratePostParam) {
        hashrateService.add(hashratePostParam);
    }

//    @ApiOperation("修改算力产品-管理员")
//    @PutMapping
//    @Role
//    public void modify(@RequestBody @Valid HashratePutParam hashratePutParam) {
//        hashrateService.update(hashratePutParam);
//    }

    @ApiOperation("删除算力产品-管理员")
    @DeleteMapping("{id}")
    @Role
    public void delete(@PathVariable("id") Long id) {
        hashrateService.delete(id);
    }
}
