package com.brqtest.service;

import com.brqtest.enuns.Status;
import com.brqtest.model.Account;
import com.brqtest.model.dto.AccountDto;
import com.brqtest.model.dto.TransferRequestDto;
import com.brqtest.repository.AccountRepository;
import com.brqtest.utils.StaticUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
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
                .number(0l)
                .balance(0.0)
                .status(Status.ACTIVE)
                .build();

        // antes de criar ver se ja existe a conta e nao deixar criar

        return accountRepository.save(accountDto.convertToEntity()).convertToDto();
    }

    @Transactional
    public Boolean transfer(TransferRequestDto transferRequestDto){

        Account sending  = accountRepository.findOneByNumberAndAgency(transferRequestDto.getSending().getNumber(), transferRequestDto.getSending().getAgency());
        Account receiving  = accountRepository.findOneByNumberAndAgency(transferRequestDto.getReceiving().getNumber(), transferRequestDto.getReceiving().getAgency());

        if(sending.getBalance() < transferRequestDto.getAmount()){
            throw new IllegalTransactionStateException("Usuario nao possui o valor da transferencia em conta");
        }else{
            StaticUtils.changeBalance(receiving,sending, transferRequestDto.getAmount());

            accountRepository.save(receiving);
            accountRepository.save(sending);

            return true;
        }
    }
}
