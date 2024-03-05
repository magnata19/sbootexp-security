package io.github.springsecurity.sbootexpsecurity.repository;

import io.github.springsecurity.sbootexpsecurity.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
