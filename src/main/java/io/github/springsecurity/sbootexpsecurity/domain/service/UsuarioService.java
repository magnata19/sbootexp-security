package io.github.springsecurity.sbootexpsecurity.domain.service;

import io.github.springsecurity.sbootexpsecurity.domain.entity.Grupo;
import io.github.springsecurity.sbootexpsecurity.domain.entity.Usuario;
import io.github.springsecurity.sbootexpsecurity.domain.entity.UsuarioGrupo;
import io.github.springsecurity.sbootexpsecurity.domain.repository.GrupoRepository;
import io.github.springsecurity.sbootexpsecurity.domain.repository.UsuarioGrupoRepository;
import io.github.springsecurity.sbootexpsecurity.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final GrupoRepository grupoRepository;
  private final UsuarioGrupoRepository usuarioGrupoRepository;
  private final PasswordEncoder encoder;

  @Transactional
  public Usuario salvar(Usuario usuario, List<String> grupos) {
    String senhaCriptografada = encoder.encode(usuario.getSenha());
    usuario.setSenha(senhaCriptografada);
    Usuario userSaved = usuarioRepository.save(usuario);

    List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
      Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);

      if (possivelGrupo.isPresent()) {
        Grupo grupo = possivelGrupo.get();

        return new UsuarioGrupo(userSaved, grupo);
      }

      return null;
    }).filter(grupo -> grupo != null).collect(Collectors.toList());
    usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

    return usuario;
  }

  public Usuario obterUsuarioComPermissoes (String login) {
    Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);

    if(usuarioOptional.isEmpty()) {
      return null;
    }
    Usuario usuario = usuarioOptional.get();
    List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
    usuario.setPermissoes(permissoes);
    return usuario;
  }
}