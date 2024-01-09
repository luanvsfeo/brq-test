package com.brqtest.controller;

import com.brqtest.model.dto.JuristicPersonDto;
import com.brqtest.model.dto.NaturalPersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NaturalPersonControllerTest {


    @LocalServerPort
    private int port;

    private String PATH = "/natural-person";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void invalidCpf(){
        NaturalPersonDto naturalPersonDto = NaturalPersonDto.builder()
                .address("av brasil")
                .name("cafe")
                .cpf(123l)
                .password("queroCafe")
                .build();

        String response =  this.restTemplate.postForObject("http://localhost:" + port + "/" + PATH, naturalPersonDto, String.class);

        assertThat(response).contains("Cpf invalido");
    }

    @Test
    void validCpf(){
        NaturalPersonDto naturalPersonDto = NaturalPersonDto.builder()
                .address("av brasil")
                .name("cafe")
                .cpf(45025283078L)
                .password("queroCafe")
                .build();

        NaturalPersonDto response =  this.restTemplate.postForObject("http://localhost:" + port + "/" + PATH, naturalPersonDto, NaturalPersonDto.class);

        assertThat(response.getCpf()).isEqualTo(45025283078L);
    }
}
