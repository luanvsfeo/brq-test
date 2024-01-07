package com.brqtest.controller;

import com.brqtest.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // todo - fazer o dto

    @PostMapping("/person/{person_id}")
    public void create(@RequestParam("person_id") long personId) {
        accountService.create(personId);
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
