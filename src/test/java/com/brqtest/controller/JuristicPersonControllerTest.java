package com.brqtest.controller;

import com.brqtest.model.dto.JuristicPersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class JuristicPersonControllerTest {

    @LocalServerPort
    private int port;

    private String PATH = "/juristic-person";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void invalidCnpj(){
        JuristicPersonDto juristicPersonDto = JuristicPersonDto.builder()
                .address("av brasil")
                .name("cafe")
                .cnpj(123l)
                .password("queroCafe")
                .build();

       String response =  this.restTemplate.postForObject("http://localhost:" + port + "/" + PATH, juristicPersonDto, String.class);

        assertThat(response).contains("Cnpj invalido");
    }

    @Test
    void validCnpj(){
        JuristicPersonDto juristicPersonDto = JuristicPersonDto.builder()
                .address("av brasil")
                .name("cafe")
                .cnpj(99783073000164L)
                .password("queroCafe")
                .build();

        JuristicPersonDto response =  this.restTemplate.postForObject("http://localhost:" + port + "/" + PATH, juristicPersonDto, JuristicPersonDto.class);

        assertThat(response.getCnpj()).isEqualTo(99783073000164L);
    }
}
