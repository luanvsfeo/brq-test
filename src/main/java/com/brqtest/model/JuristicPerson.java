package com.brqtest.model;


import com.brqtest.model.dto.JuristicPersonDto;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JuristicPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long cnpj;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    // colcoar aqui uma lista de contas

    public JuristicPersonDto convertToDto() {
        return JuristicPersonDto.builder()
                .address(this.address)
                .cnpj(this.cnpj)
                .name(this.name)
                .build();
    }

}
