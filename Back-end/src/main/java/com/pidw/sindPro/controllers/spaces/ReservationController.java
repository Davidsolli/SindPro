package com.pidw.sindPro.controllers.spaces;

import com.pidw.sindPro.dtos.spaces.ReservationDTO;
import com.pidw.sindPro.service.spaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO reservationDTO) {
        reservationDTO = reservationService.create(reservationDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservationDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(reservationDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable Long id) {
        ReservationDTO reservationDTO = reservationService.findById(id);
        return ResponseEntity.ok(reservationDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll() {
        List<ReservationDTO> result = reservationService.findAll();
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        reservationDTO = reservationService.update(id, reservationDTO);
        return ResponseEntity.ok(reservationDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
