package com.pidw.sindPro.service;

import com.pidw.sindPro.domains.Morador;
import com.pidw.sindPro.domains.Unidade;
import com.pidw.sindPro.domains.Usuario;
import com.pidw.sindPro.dtos.MoradorDTO;
import com.pidw.sindPro.repositories.MoradorRepository;
import com.pidw.sindPro.repositories.UnidadeRepository;
import com.pidw.sindPro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public MoradorDTO create(MoradorDTO moradorDTO) {
        Morador morador = new Morador();
        Unidade unidade = unidadeRepository.getReferenceById(moradorDTO.getUnidadeId());
        Usuario usuario = usuarioRepository.getReferenceById(moradorDTO.getUsuarioId());

        morador.setUnidade(unidade);
        morador.setUsuario(usuario);

        copyDtoToEntity(morador, moradorDTO);

        morador = moradorRepository.save(morador);

        return new MoradorDTO(morador);
    }

    private void copyDtoToEntity(Morador morador, MoradorDTO moradorDTO) {
        morador.setDataInicio(moradorDTO.getDataInicio());
        morador.setDataFim(moradorDTO.getDataFim());
        morador.setCreatedAt(moradorDTO.getCreatedAt());
        morador.setUpdatedAt(moradorDTO.getUpdatedAt());
    }
}
