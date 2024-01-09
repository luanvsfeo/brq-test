package com.brqtest.model.dto;

import com.brqtest.model.NaturalPerson;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
