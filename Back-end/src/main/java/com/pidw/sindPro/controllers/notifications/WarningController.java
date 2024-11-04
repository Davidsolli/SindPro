package com.pidw.sindPro.controllers.notifications;

import com.pidw.sindPro.dtos.notifications.WarningDTO;
import com.pidw.sindPro.service.notifications.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/warnings")
public class WarningController {

    @Autowired
    private WarningService warningService;

    @PostMapping
    public ResponseEntity<WarningDTO> create(@RequestBody WarningDTO warningDTO) {
        warningDTO = warningService.create(warningDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(warningDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(warningDTO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<WarningDTO>> findALl() {
        List<WarningDTO> result = warningService.findAll();
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WarningDTO> update(@PathVariable Long id, @RequestBody WarningDTO warningDTO) {
        warningDTO = warningService.update(id, warningDTO);
        return ResponseEntity.ok(warningDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        warningService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
