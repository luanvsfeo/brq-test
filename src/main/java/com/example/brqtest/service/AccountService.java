package com.example.brqtest.service;

import com.example.brqtest.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    //todo - colocar log e os metodos ne

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void create(){

    }

    @Transactional
    public void transfer(){
        // colocar a parte de comunicacao com a api externa



        /*
        receber o numero da conta da pessoa logada, ja que ele pode ter mais de uma
        buscar no banco a conta da pessoa que vai receber a quantia
        verificar se a conta que esta enviando o dinheiro tem o saldo necessario, se nao jogar mensagem de 400
         */
    }

    public void statement(){

    }

}
