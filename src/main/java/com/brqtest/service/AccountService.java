package com.brqtest.service;

import com.brqtest.enuns.Status;
import com.brqtest.model.Account;
import com.brqtest.model.JuristicPerson;
import com.brqtest.model.NaturalPerson;
import com.brqtest.model.dto.AccountDto;
import com.brqtest.model.dto.TransferRequestDto;
import com.brqtest.repository.AccountRepository;
import com.brqtest.utils.StaticUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    //todo - colocar log e os metodos ne

    private final NaturalPersonService naturalPersonService;

    private final JuristicPersonService juristicPersonService;

    private final AccountRepository accountRepository;

    public AccountService(NaturalPersonService naturalPersonService, JuristicPersonService juristicPersonService, AccountRepository accountRepository) {
        this.naturalPersonService = naturalPersonService;
        this.juristicPersonService = juristicPersonService;
        this.accountRepository = accountRepository;
    }

    public AccountDto create(Long personDocument) throws InvalidParameterException {

        AccountDto accountDto = AccountDto.builder()
                .agency(generateAgency())
                .number(generateNumber())
                .balance(0.0)
                .status(Status.ACTIVE)
                .build();

        Account account = accountDto.convertToEntity();

        if (StaticUtils.isCpf(personDocument.toString())) {
            Optional<NaturalPerson> naturalPerson = naturalPersonService.findByCpf(personDocument.longValue());
            naturalPerson.ifPresent(account::setNaturalPerson);
        } else {
            Optional<JuristicPerson> juristicPerson = juristicPersonService.findByCnpj(personDocument.longValue());
            juristicPerson.ifPresent(account::setJuristicPerson);
        }

        return accountRepository.save(account).convertToDto();
    }

    @Transactional
    public Boolean transfer(TransferRequestDto transferRequestDto) {

        Account sending = accountRepository.findOneByNumberAndAgency(transferRequestDto.getSending().getNumber(), transferRequestDto.getSending().getAgency());
        Account receiving = accountRepository.findOneByNumberAndAgency(transferRequestDto.getReceiving().getNumber(), transferRequestDto.getReceiving().getAgency());

        if (sending.getBalance() < transferRequestDto.getAmount()) {
            throw new IllegalTransactionStateException("Usuario nao possui o valor da transferencia em conta");
        } else {
            StaticUtils.changeBalance(receiving, sending, transferRequestDto.getAmount());

            accountRepository.save(receiving);
            accountRepository.save(sending);

            return true;
        }
    }

    private Long generateAgency() throws InvalidParameterException {
        int count = 0;
        while (true) {
            Integer agencyInt = getRandomNumberUsingNextInt(1000, 9999);
            Long agency = Long.parseLong(agencyInt.toString());

            if (!alreadyExistsThisAgency(agency)) {
                return agency;
            }
            count = count + 1;

            if (count == 10) {
                throw new InvalidParameterException("Ocorreu um erro ao gerar a agencia");
            }
        }
    }

    private Long generateNumber() throws InvalidParameterException {
        int count = 0;
        while (true) {
            Integer numberInt = getRandomNumberUsingNextInt(1000000, 9999999);
            Long number = Long.parseLong(numberInt.toString());

            if (!alreadyExistsThisNumber(number)) {
                return number;
            }
            count = count + 1;

            if (count == 10) {
                throw new InvalidParameterException("Ocorreu um erro ao gerar a numero da conta");
            }
        }
    }

    private boolean alreadyExistsThisAgency(Long agency) {
        return accountRepository.existsByAgency(agency);
    }

    private boolean alreadyExistsThisNumber(Long number) {
        return accountRepository.existsByNumber(number);
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
