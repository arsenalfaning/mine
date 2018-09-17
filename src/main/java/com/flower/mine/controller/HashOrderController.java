package com.flower.mine.controller;

import com.flower.mine.bean.HashOrder;
import com.flower.mine.param.HashOrderParam;
import com.flower.mine.ret.HashOrderResult;
import com.flower.mine.service.HashOrderService;
import com.flower.mine.util.Role;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/hash-orders")
public class HashOrderController {

    @Autowired
    private HashOrderService hashOrderService;

    @ApiOperation("下订单")
    @PostMapping()
    public HashOrderResult hashOrder(@RequestBody @Valid HashOrderParam hashOrderParam) {
        return hashOrderService.hashOrder(hashOrderParam);
    }

    @ApiOperation("确认订单生效-管理员")
    @PostMapping("{id}/succeed")
    @Role
    public void hashOrderSuccess(@PathVariable Long id) {
        hashOrderService.hashOrderSuccess(id);
    }

    @ApiOperation("订单分页查询-管理员")
    @GetMapping
    @Role
    public Page<HashOrder> page(@RequestParam int page, @RequestParam int size) {
        return hashOrderService.page(page, size);
    }
}
