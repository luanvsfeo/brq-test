package com.example.brqtest.controller;


import com.example.brqtest.service.NaturalPersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/natural-person")
public class NaturalPersonController {
    private final NaturalPersonService naturalPersonService;

    public NaturalPersonController(NaturalPersonService naturalPersonService) {
        this.naturalPersonService = naturalPersonService;
    }


    @PostMapping
    public void create() {
        naturalPersonService.create();
    }

    @PutMapping
    public void update() {
        naturalPersonService.update();
    }
}
