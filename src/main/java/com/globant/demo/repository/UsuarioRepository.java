package com.globant.demo.repository;

import com.globant.demo.entity.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, String> {
}
