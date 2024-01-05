package com.brqtest.controller;


import com.brqtest.model.dto.JuristicPersonDto;
import com.brqtest.service.JuristicPersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/juristic-person")
public class JuristicPersonController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final JuristicPersonService juristicPersonService;

    public JuristicPersonController(JuristicPersonService juristicPersonService) {
        this.juristicPersonService = juristicPersonService;
    }

    @PostMapping
    public JuristicPersonDto create(@Valid JuristicPersonDto juristicPersonDto) {
        log.info("m=create; step=start");
        JuristicPersonDto juristicPersonCreated = juristicPersonService.create(juristicPersonDto);
        log.info("m=create; step=finished; {}", juristicPersonCreated);
        return juristicPersonCreated;
    }
}
