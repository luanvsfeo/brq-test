package com.brqtest.model.dto;

import com.brqtest.enuns.Status;
import com.brqtest.model.Account;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotNull
    private Long number;
    @NotNull
    private Long agency;
    private double balance;
    private Status status;

    public Account convertToEntity() {
        return Account.builder()
                .agency(this.agency)
                .number(this.number)
                .balance(this.balance)
                .status(this.status)
                .build();
    }
}
