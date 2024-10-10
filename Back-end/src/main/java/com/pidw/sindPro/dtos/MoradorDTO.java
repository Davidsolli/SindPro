package com.pidw.sindPro.dtos;

import com.pidw.sindPro.domains.Morador;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MoradorDTO {

    private Long id;
    private Long usuarioId;
    private Long unidadeId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MoradorDTO(Morador morador) {
        id = morador.getId();
        usuarioId = morador.getUsuario().getId();
        unidadeId = morador.getUnidade().getId();
        dataInicio = morador.getDataInicio();
        dataFim = morador.getDataFim();
        createdAt = morador.getCreatedAt();
        updatedAt = morador.getUpdatedAt();
    }
}
