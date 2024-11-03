package com.pidw.sindPro.controllers.notifications;

import com.pidw.sindPro.dtos.notifications.NotificationDTO;
import com.pidw.sindPro.service.notifications.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO notificationDTO) {
        notificationDTO = notificationService.create(notificationDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(notificationDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(notificationDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NotificationDTO> findById(@PathVariable Long id) {
        NotificationDTO notificationDTO = notificationService.findById(id);
        return ResponseEntity.ok(notificationDTO);
    }

    @GetMapping(value = "/all/{receiverId}")
    public ResponseEntity<Page<NotificationDTO>> findAll(Pageable pageable, @PathVariable Long receiverId) {
        Page<NotificationDTO> result = notificationService.findAllByReceiver(pageable, receiverId);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) {
        notificationDTO = notificationService.update(notificationDTO, id);
        return ResponseEntity.ok(notificationDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
