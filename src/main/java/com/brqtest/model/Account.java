package com.brqtest.model;

import com.brqtest.enuns.Status;
import com.brqtest.model.dto.AccountDto;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private Long agency;

    @Column(nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne
    @JoinColumn(name = "natural_person_id")
    private NaturalPerson naturalPerson;

    @ManyToOne
    @JoinColumn(name = "juristic_person_id")
    private JuristicPerson juristicPerson;


    public AccountDto convertToDto() {
        return AccountDto.builder()
                .agency(this.agency)
                .number(this.number)
                .balance(this.balance)
                .build();
    }


    public void growBalance(Double amount){
        this.setBalance(this.getBalance() + amount);
    }

}
