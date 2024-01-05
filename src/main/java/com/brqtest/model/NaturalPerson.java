package com.brqtest.model;

import com.brqtest.model.dto.NaturalPersonDto;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long cpf;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    // colcoar aqui uma lista de contas

    public NaturalPersonDto convertToDto() {
        return NaturalPersonDto.builder()
                .address(this.address)
                .cpf(this.cpf)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
