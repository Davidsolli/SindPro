package com.pidw.sindPro.controllers.spaces;

import com.pidw.sindPro.dtos.spaces.CommonSpacesDTO;
import com.pidw.sindPro.service.spaces.CommonSpacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/common-spaces")
public class CommonSpacesController {

    @Autowired
    private CommonSpacesService commonSpacesService;

    @PostMapping
    public ResponseEntity<CommonSpacesDTO> create(@RequestBody CommonSpacesDTO commonSpacesDTO) {
        commonSpacesDTO = commonSpacesService.create(commonSpacesDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(commonSpacesDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(commonSpacesDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommonSpacesDTO> findById(@PathVariable Long id) {
        CommonSpacesDTO commonSpacesDTO = commonSpacesService.findById(id);
        return ResponseEntity.ok(commonSpacesDTO);
    }

    @GetMapping
    public ResponseEntity<List<CommonSpacesDTO>> findAll() {
        List<CommonSpacesDTO> result = commonSpacesService.findAll();
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommonSpacesDTO> update(@RequestBody CommonSpacesDTO commonSpacesDTO, @PathVariable Long id) {
        commonSpacesDTO = commonSpacesService.update(commonSpacesDTO, id);
        return ResponseEntity.ok(commonSpacesDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commonSpacesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
