package com.brqtest.controller;

import com.brqtest.model.dto.AccountDto;
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

    @PostMapping("/person/{person_document}")
    public AccountDto create(@RequestParam("person_document") long personDocument) {
        return accountService.create(personDocument);
    }

    @PostMapping("/transfer")
    public Boolean makeBankTransfer(@Valid @RequestBody TransferRequestDto transferRequestDto) {
        return accountService.transfer(transferRequestDto);
    }

}
