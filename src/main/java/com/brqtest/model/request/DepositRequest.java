package com.brqtest.model.request;

import com.brqtest.model.dto.AccountDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class DepositRequest {

    @NotNull
    private AccountDto accountDto;

    @NotNull
    private Double amount;
}
