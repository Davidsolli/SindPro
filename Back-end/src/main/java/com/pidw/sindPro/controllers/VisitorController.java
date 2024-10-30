package com.pidw.sindPro.controllers;

import com.pidw.sindPro.dtos.users.VisitorDTO;
import com.pidw.sindPro.service.users.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping(value = "/{userId}")
    public ResponseEntity<VisitorDTO> create(@RequestBody VisitorDTO visitorDTO, @PathVariable Long userId) {
        visitorDTO = visitorService.create(visitorDTO, userId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}").buildAndExpand(visitorDTO.getUserId()).toUri();
        return ResponseEntity.created(uri).body(visitorDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VisitorDTO> findById(@PathVariable Long id) {
        VisitorDTO visitorDTO = visitorService.findById(id);
        return ResponseEntity.ok(visitorDTO);
    }

    @GetMapping(value = "/all/{userId}")
    public ResponseEntity<List<VisitorDTO>> findAll(@PathVariable Long userId) {
        List<VisitorDTO> result = visitorService.findAll(userId);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VisitorDTO> update(@PathVariable Long id, @RequestBody VisitorDTO visitorDTO) {
        visitorDTO = visitorService.update(id, visitorDTO);
        return ResponseEntity.ok(visitorDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        visitorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
