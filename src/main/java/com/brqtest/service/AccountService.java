package com.brqtest.service;

import com.brqtest.enuns.Status;
import com.brqtest.model.Account;
import com.brqtest.model.JuristicPerson;
import com.brqtest.model.NaturalPerson;
import com.brqtest.model.dto.AccountDto;
import com.brqtest.model.request.DepositRequest;
import com.brqtest.model.request.TransferRequest;
import com.brqtest.repository.AccountRepository;
import com.brqtest.utils.StaticUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;
import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    String PATH_NOTIFICATION_SERVICE = "https://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3";

    private final NaturalPersonService naturalPersonService;
    private final JuristicPersonService juristicPersonService;
    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public AccountService(NaturalPersonService naturalPersonService, JuristicPersonService juristicPersonService, AccountRepository accountRepository) {
        this.naturalPersonService = naturalPersonService;
        this.juristicPersonService = juristicPersonService;
        this.accountRepository = accountRepository;
    }

    public AccountDto create(Long personDocument) throws InvalidParameterException {

        log.info("m=create; step=start");

        AccountDto accountDto = AccountDto.builder()
                .agency(generateAgency())
                .number(generateNumber())
                .balance(10.0)
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

        account = accountRepository.save(account);
        log.info("m=create; step=finished; {}", account);

        return account.convertToDto();
    }

    public Boolean deposit(DepositRequest depositRequest) {

        Account account = accountRepository.findByNumberAndAgency(depositRequest.getAccountDto().getNumber(), depositRequest.getAccountDto().getAgency());

        if (account != null) {
            account.growBalance(depositRequest.getAmount());
            accountRepository.save(account);
            sendNotification();
            return true;
        }
        return false;
    }


    @Transactional
    public Boolean transfer(TransferRequest transferRequest) {

        log.info("m=transfer; step=start");

        Account sending = accountRepository.findByNumberAndAgency(transferRequest.getSending().getNumber(), transferRequest.getSending().getAgency());
        Account receiving = accountRepository.findByNumberAndAgency(transferRequest.getReceiving().getNumber(), transferRequest.getReceiving().getAgency());

        if (sending.getBalance() < transferRequest.getAmount()) {
            throw new IllegalTransactionStateException("Usuario nao possui o valor da transferencia em conta");
        } else {
            StaticUtils.changeBalance(receiving, sending, transferRequest.getAmount());

            accountRepository.save(receiving);
            accountRepository.save(sending);

            log.info("m=transfer; step=finished; receiving={}; sending={}", receiving, sending);

            return true;
        }
    }


    private void sendNotification() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(PATH_NOTIFICATION_SERVICE, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

        } catch (Exception e) {
            log.warn("Ocorreu um erro ao enviar a notificacao");
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
