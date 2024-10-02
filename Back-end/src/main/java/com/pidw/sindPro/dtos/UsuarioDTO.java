package com.pidw.sindPro.dtos;

import com.pidw.sindPro.domains.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Usuario.TipoUsuario tipo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UsuarioDTO(Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        senha = usuario.getSenha();
        tipo = usuario.getTipo();
        createdAt = usuario.getCreatedAt();
        updatedAt = usuario.getUpdatedAt();
    }

    public enum TipoUsuario {
        MORADOR, ADMINISTRADOR, PORTEIRO
    }
}
