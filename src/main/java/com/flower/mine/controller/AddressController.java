package com.flower.mine.controller;

import com.flower.mine.bean.Address;
import com.flower.mine.param.AddressParam;
import com.flower.mine.ret.DataResult;
import com.flower.mine.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation("地址新增/修改")
    @PostMapping()
    public void addOrUpdate(@RequestBody @Valid AddressParam addressParam) {
        addressService.addOrUpdate(addressParam);
    }

    @ApiOperation("地址删除")
    @DeleteMapping()
    public void delete() {
        addressService.delete();
    }

    @ApiOperation("查询自己地址")
    @GetMapping
    public DataResult<Address> address() {
        return addressService.address();
    }
}
