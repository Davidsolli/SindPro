package com.pidw.sindPro.service;

import com.pidw.sindPro.domains.Usuario;
import com.pidw.sindPro.dtos.UsuarioDTO;
import com.pidw.sindPro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTipo(Usuario.TipoUsuario.valueOf(usuarioDTO.getTipo().name()));

        usuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }
}
