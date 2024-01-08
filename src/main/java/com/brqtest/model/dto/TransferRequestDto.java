package com.brqtest.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequestDto {

    @NotNull
    private AccountDto sending;

    @NotNull
    private AccountDto receiving;

    @NotNull
    private Double amount;
}
