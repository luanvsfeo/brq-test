package com.brqtest.controller;

import com.brqtest.model.dto.AccountDto;
import com.brqtest.model.request.DepositRequest;
import com.brqtest.model.request.TransferRequest;
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
    public Boolean makeBankTransfer(@Valid @RequestBody TransferRequest transferRequest) {
        return accountService.transfer(transferRequest);
    }

    @PostMapping("/deposit")
    public Boolean makeDeposit(@Valid @RequestBody DepositRequest depositRequest) {
        return accountService.deposit(depositRequest);
    }

}
