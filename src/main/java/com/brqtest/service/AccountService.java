package com.brqtest.service;

import com.brqtest.enuns.Status;
import com.brqtest.model.dto.AccountDto;
import com.brqtest.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    //todo - colocar log e os metodos ne

  private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public AccountDto create(Long personId){

        AccountDto accountDto = AccountDto.builder()
                .agency(0l)
                .balance(1l)
                .number(0l)
                .status(Status.ACTIVE)
                .build();

        return accountRepository.save(accountDto.convertToEntity()).convertToDto();
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
