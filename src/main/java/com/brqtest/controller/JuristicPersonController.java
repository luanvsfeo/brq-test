package com.brqtest.controller;


import com.brqtest.service.JuristicPersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/juristic-person")
public class JuristicPersonController {

    private final JuristicPersonService juristicPersonService;

    public JuristicPersonController(JuristicPersonService juristicPersonService) {
        this.juristicPersonService = juristicPersonService;
    }


    @PostMapping
    public void create() {
        juristicPersonService.create();
    }

    @PutMapping
    public void update() {
        juristicPersonService.update();
    }
}
