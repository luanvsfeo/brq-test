package com.brqtest.controller;

import com.brqtest.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // todo - fazer o dto

    @PostMapping
    public void create() {
        accountService.create();
    }

    @GetMapping("/statement")
    public void getBankStatement() {
        accountService.statement();
    }

    @PostMapping("/transfer")
    public void makeBankTransfer() {
        accountService.transfer();
    }

}
