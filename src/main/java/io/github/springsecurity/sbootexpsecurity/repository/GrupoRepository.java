package io.github.springsecurity.sbootexpsecurity.repository;

import io.github.springsecurity.sbootexpsecurity.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

  Optional<Grupo> findByNome(String string);
}
