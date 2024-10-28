package com.pidw.sindPro.controllers.notifications;

import com.pidw.sindPro.dtos.notifications.AnnouncementDTO;
import com.pidw.sindPro.service.notifications.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<AnnouncementDTO> create(@RequestBody AnnouncementDTO announcementDTO) {
        announcementDTO = announcementService.create(announcementDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(announcementDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(announcementDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnnouncementDTO> findById(@PathVariable Long id) {
        AnnouncementDTO announcementDTO = announcementService.findById(id);
        return ResponseEntity.ok(announcementDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AnnouncementDTO>> findAll(Pageable pageable) {
        Page<AnnouncementDTO> result = announcementService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AnnouncementDTO> update(@PathVariable Long id, @RequestBody AnnouncementDTO announcementDTO) {
        announcementDTO = announcementService.update(announcementDTO, id);
        return ResponseEntity.ok(announcementDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
