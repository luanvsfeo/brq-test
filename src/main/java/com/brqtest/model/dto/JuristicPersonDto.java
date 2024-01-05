package com.brqtest.model.dto;

import com.brqtest.model.JuristicPerson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JuristicPersonDto {

    @CNPJ
    private Long cnpj;

    @NotNull(message = "name is a required field")
    private String name;

    @NotNull(message = "address is a required field")
    private String address;

    @NotNull(message = "password is a required field")
    @JsonIgnore
    private String password;

    public JuristicPerson convertToEntity() {
        return JuristicPerson.builder()
                .address(this.address)
                .cnpj(this.cnpj)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
