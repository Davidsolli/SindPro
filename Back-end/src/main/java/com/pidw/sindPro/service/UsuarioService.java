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

        copyDtoToEntity(usuario, usuarioDTO);

        usuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return new UsuarioDTO(usuario);
    }

    public void copyDtoToEntity(Usuario usuario, UsuarioDTO usuarioDTO) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTipo(Usuario.TipoUsuario.valueOf(usuarioDTO.getTipo().name()));
    }
}
