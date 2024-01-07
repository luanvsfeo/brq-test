package com.brqtest.controller;


import com.brqtest.model.dto.NaturalPersonDto;
import com.brqtest.service.NaturalPersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/natural-person")
public class NaturalPersonController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final NaturalPersonService naturalPersonService;

    public NaturalPersonController(NaturalPersonService naturalPersonService) {
        this.naturalPersonService = naturalPersonService;
    }

    @PostMapping
    public NaturalPersonDto create(@Valid @RequestBody NaturalPersonDto naturalPersonDto) {
        log.info("m=create; step=start");
        NaturalPersonDto naturalPersonDtoCreated = naturalPersonService.create(naturalPersonDto);
        log.info("m=create; step=finished; {}", naturalPersonDtoCreated);
        return naturalPersonDtoCreated;
    }

}
