package com.brqtest.service;

import com.brqtest.model.NaturalPerson;
import com.brqtest.model.dto.NaturalPersonDto;
import com.brqtest.repository.NaturalPersonRepository;
import com.brqtest.utils.StaticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class NaturalPersonService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final NaturalPersonRepository naturalPersonRepository;

    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
    }

    public Optional<NaturalPerson> findByCpf(Long cpf) {
        return naturalPersonRepository.findOneByCpf(cpf);
    }

    public NaturalPersonDto create(NaturalPersonDto naturalPersonDto) {
        log.info("m=create; step=start");

        if (StaticUtils.isCpf(naturalPersonDto.getCpf().toString())) {
            try {
                NaturalPersonDto naturalPersonDtoCreated = naturalPersonRepository.save(naturalPersonDto.convertToEntity()).convertToDto();
                log.info("m=create; step=finished; {}", naturalPersonDtoCreated);
                return naturalPersonDtoCreated;
            } catch (RuntimeException e) {
                log.warn("m=create; e=" + e.getMessage(), e);
                return null;
            }
        } else {
            throw new InvalidParameterException("Cpf invalido");

        }
    }
}
