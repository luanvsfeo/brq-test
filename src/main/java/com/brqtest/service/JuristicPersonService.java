package com.brqtest.service;

import com.brqtest.model.JuristicPerson;
import com.brqtest.model.dto.JuristicPersonDto;
import com.brqtest.repository.JuristicPersonRepository;
import com.brqtest.utils.StaticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class JuristicPersonService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final JuristicPersonRepository juristicPersonRepository;

    public JuristicPersonService(JuristicPersonRepository juristicPersonRepository) {
        this.juristicPersonRepository = juristicPersonRepository;
    }

    public JuristicPersonDto create(JuristicPersonDto juristicPersonDto) throws InvalidParameterException {
        log.info("m=create; step=start");

        if (StaticUtils.isCnpj(juristicPersonDto.getCnpj().toString())) {
            try {
                JuristicPerson juristicPerson = juristicPersonDto.convertToEntity();
                juristicPerson.hashPassword();
                juristicPerson = juristicPersonRepository.save(juristicPerson);
                log.info("m=create; step=finished; {}", juristicPerson);

                return juristicPerson.convertToDtoWithoutPassword();
            } catch (RuntimeException e) {
                log.warn("m=create; e=" + e.getMessage(), e);
                return null;
            }
        } else {
            throw new InvalidParameterException("Cnpj invalido");
        }
    }

    public Optional<JuristicPerson> findByCnpj(Long cnpj) {
        return juristicPersonRepository.findOneByCnpj(cnpj);
    }
}
