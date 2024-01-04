package com.brqtest.service;

import com.brqtest.repository.JuristicPersonRepository;
import org.springframework.stereotype.Service;

@Service
public class JuristicPersonService {

    //todo - colocar log e os metodos ne

    private final JuristicPersonRepository juristicPersonRepository;

    public JuristicPersonService(JuristicPersonRepository juristicPersonRepository) {
        this.juristicPersonRepository = juristicPersonRepository;
    }


    public void create() {
    }

    public void update() {
    }
}
