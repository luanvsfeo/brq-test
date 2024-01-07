package com.brqtest.model.dto;

import com.brqtest.model.NaturalPerson;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NaturalPersonDto {

    @NotNull(message = "cpf is a required field")
    private Long cpf;

    @NotNull(message = "name is a required field")
    private String name;

    @NotNull(message = "address is a required field")
    private String address;

    @NotNull(message = "password is a required field")
    private String password;

    public NaturalPerson convertToEntity() {
        return NaturalPerson.builder()
                .address(this.address)
                .cpf(this.cpf)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
