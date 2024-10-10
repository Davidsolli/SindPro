package com.pidw.sindPro.controllers;

import com.pidw.sindPro.dtos.MoradorDTO;
import com.pidw.sindPro.service.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/morador")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    // Criar CRUD de Unidade para evitar integridade referencial.
    @PostMapping
    public ResponseEntity<MoradorDTO> create(@RequestBody MoradorDTO moradorDTO) {
        moradorDTO = moradorService.create(moradorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(moradorDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(moradorDTO);
    }
}
