package io.github.springsecurity.sbootexpsecurity.api;

import io.github.springsecurity.sbootexpsecurity.domain.entity.Grupo;
import io.github.springsecurity.sbootexpsecurity.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GrupoController {

  private final GrupoRepository repository;

  @PostMapping
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo) {
    Grupo grupoSaved = repository.save(grupo);
    return ResponseEntity.ok(grupoSaved);
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<Grupo>> listar() {
    List<Grupo> grupos = repository.findAll();
    return ResponseEntity.ok(grupos);
  }
}
