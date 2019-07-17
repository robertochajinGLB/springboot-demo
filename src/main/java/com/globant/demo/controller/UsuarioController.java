package com.globant.demo.controller;

import com.globant.demo.dto.UsuarioDTO;
import com.globant.demo.entity.Usuario;
import com.globant.demo.service.BaseService;
import com.globant.demo.service.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private BaseService service;

  public UsuarioController(UsuarioServiceImpl service) {
    this.service = service;
  }

  @GetMapping(path = "/{id}")
  Mono<Usuario> findById(@PathVariable String id) {
    return service.findById(id);
  }

  @GetMapping
  Flux<Usuario> findAll() {
    return service.findAll();
  }

  @PostMapping
  Mono<Usuario> create(@RequestBody UsuarioDTO usuario) {
    return service
        .create(Usuario.builder().id(usuario.getId()).nombre(usuario.getNombre()).build());
  }
}
