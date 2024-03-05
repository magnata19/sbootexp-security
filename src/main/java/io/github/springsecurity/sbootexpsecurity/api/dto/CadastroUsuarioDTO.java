package io.github.springsecurity.sbootexpsecurity.api.dto;

import io.github.springsecurity.sbootexpsecurity.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
  private Usuario usuario;
  private List<String> permissoes;
}
