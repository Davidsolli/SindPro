package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
