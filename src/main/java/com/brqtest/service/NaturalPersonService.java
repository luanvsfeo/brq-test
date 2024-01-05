package com.brqtest.service;

import com.brqtest.model.dto.NaturalPersonDto;
import com.brqtest.repository.NaturalPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.lang.invoke.MethodHandles;

@Service
public class NaturalPersonService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final NaturalPersonRepository naturalPersonRepository;

    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
    }

    public NaturalPersonDto create(NaturalPersonDto naturalPersonDto) {
        log.info("m=create; step=start");
        NaturalPersonDto naturalPersonDtoCreated = naturalPersonRepository.save(naturalPersonDto.convertToEntity()).convertToDto();
        log.info("m=create; step=finished; {}", naturalPersonDtoCreated);
        return naturalPersonDtoCreated;
    }
}
