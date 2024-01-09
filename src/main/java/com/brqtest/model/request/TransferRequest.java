package com.brqtest.model.request;

import com.brqtest.model.dto.AccountDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequest {

    @NotNull
    private AccountDto sending;

    @NotNull
    private AccountDto receiving;

    @NotNull
    private Double amount;
}
