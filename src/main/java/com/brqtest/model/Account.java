package com.brqtest.model;

import com.brqtest.enuns.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  // @Id
   //@GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private Long number;  //[chave composta de id e agencia]
   private Long agency ;
   private double balance;

   @Enumerated(EnumType.STRING)
   private Status status;

}
