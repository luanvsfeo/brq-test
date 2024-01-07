package com.brqtest.model.dto;

import com.brqtest.enuns.Status;
import com.brqtest.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long number;
    private Long agency;
    private double balance;
    private Status status;

    public Account convertToEntity() {
        return Account.builder().build();
    }
}
