package com.brqtest.model;

import com.brqtest.model.dto.NaturalPersonDto;
import com.brqtest.utils.StaticUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy="naturalPerson")
    private List<Account> accounts;

    public NaturalPersonDto convertToDto() {
        return NaturalPersonDto.builder()
                .address(this.address)
                .cpf(this.cpf)
                .name(this.name)
                .password(this.password)
                .build();
    }

    public NaturalPersonDto convertToDtoWithoutPassword() {
        return NaturalPersonDto.builder()
                .address(this.address)
                .cpf(this.cpf)
                .name(this.name)
                .build();
    }

    public void hashPassword(){
        this.setPassword(StaticUtils.hashingPassword(this.password));
    }
}
