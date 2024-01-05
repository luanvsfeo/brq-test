package com.brqtest.service;

import com.brqtest.model.dto.JuristicPersonDto;
import com.brqtest.repository.JuristicPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class JuristicPersonService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final JuristicPersonRepository juristicPersonRepository;

    public JuristicPersonService(JuristicPersonRepository juristicPersonRepository) {
        this.juristicPersonRepository = juristicPersonRepository;
    }

    public JuristicPersonDto create(JuristicPersonDto juristicPersonDto) {
        log.info("m=create; step=start");
        JuristicPersonDto juristicPersonDtoCreated = juristicPersonRepository.save(juristicPersonDto.convertToEntity()).convertToDto();
        log.info("m=create; step=finished; {}",juristicPersonDtoCreated);
        return juristicPersonDtoCreated;
    }

}
