package com.brqtest.controller;

import com.brqtest.model.dto.TransferRequestDto;
import com.brqtest.service.AccountService;
import jakarta.validation.Valid;
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

     @PostMapping("/transfer")
    public void makeBankTransfer(@Valid @RequestBody TransferRequestDto transferRequestDto) {
        accountService.transfer(transferRequestDto);
    }

}
