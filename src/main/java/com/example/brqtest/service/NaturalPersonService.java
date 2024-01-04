package com.example.brqtest.service;

import com.example.brqtest.repository.NaturalPersonRepository;
import org.springframework.stereotype.Service;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository naturalPersonRepository;

    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
    }

    public void create() {
    }

    public void update() {
    }
}
