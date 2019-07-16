package com.globant.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.demo.model.Usuario;
import com.globant.demo.repository.UsuarioRepository;
import com.globant.demo.service.UsuarioServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
public class UsuarioControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UsuarioServiceImpl service;

  @MockBean
  private UsuarioRepository repository;

  Usuario usuario1 = new Usuario("123abc", "usuario test");

  @Before
  public void setUp() throws Exception {
    Mockito
        .when(this.service.findById("123abc"))
        .thenReturn(Mono.just(usuario1));
  }

  @Test
  public void findByIdTest() throws JsonProcessingException {
    String jsonBlob = objectMapper
        .writeValueAsString(usuario1);

    WebTestClient
        .bindToController(new UsuarioController(service))
        .build()
        .get().uri("/usuario/123abc")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .json(jsonBlob);
  }
}