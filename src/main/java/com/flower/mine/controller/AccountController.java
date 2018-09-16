package com.flower.mine.controller;

import com.flower.mine.bean.Account;
import com.flower.mine.service.AccountService;
import com.flower.mine.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("accounts")
    @Role
    public Page<Account> accounts(@RequestParam int page, @RequestParam int size) {
        return accountService.page(page, size);
    }
}
