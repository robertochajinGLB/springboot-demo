package com.globant.demo.service;

import com.globant.demo.model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService {

  Mono<Usuario> findById(String id);

  Flux<Usuario> findAll();

  Mono<Usuario> create(Usuario usuario);
}
