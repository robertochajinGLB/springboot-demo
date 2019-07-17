package com.globant.demo.service;


import com.globant.demo.entity.Usuario;
import com.globant.demo.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl implements BaseService {

  @Autowired
  private UsuarioRepository repository;

  @Override
  public Mono<Usuario> findById(String id) {
    return Optional.ofNullable(repository.findById(id)).orElseThrow(RuntimeException::new);
  }

  @Override
  public Flux<Usuario> findAll() {
    return Optional.ofNullable(repository.findAll()).orElseThrow(RuntimeException::new);
  }

  @Override
  public Mono<Usuario> create(Usuario usuario) {
    return Optional.ofNullable(repository.save(usuario)).orElseThrow(RuntimeException::new);
  }
}
