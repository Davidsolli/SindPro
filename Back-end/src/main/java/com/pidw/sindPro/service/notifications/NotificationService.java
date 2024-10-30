package com.pidw.sindPro.service.notifications;

import com.pidw.sindPro.domains.notifications.Notification;
import com.pidw.sindPro.dtos.notifications.NotificationDTO;
import com.pidw.sindPro.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public NotificationDTO create(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        createEntity(notificationDTO, notification);
        notification = notificationRepository.save(notification);
        return new NotificationDTO(notification);
    }

    @Transactional(readOnly = true)
    public NotificationDTO findById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        return new NotificationDTO(notification);
    }

    @Transactional(readOnly = true)
    public Page<NotificationDTO> findAll(Pageable pageable) {
        Page<Notification> result = notificationRepository.findAll(pageable);
        return result.map(NotificationDTO::new);
    }

    @Transactional
    public NotificationDTO update(NotificationDTO notificationDTO, Long id) {
        Notification notification = notificationRepository.getReferenceById(id);
        updateEntity(notificationDTO, notification);
        notification = notificationRepository.save(notification);
        return new NotificationDTO(notification);
    }

    @Transactional
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    private void createEntity(NotificationDTO notificationDTO, Notification notification) {
        notification.setTitle(notificationDTO.getTitle());
        notification.setMessage(notificationDTO.getMessage());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
    }

    private void updateEntity(NotificationDTO notificationDTO, Notification notification) {
        notification.setTitle(notificationDTO.getTitle());
        notification.setMessage(notificationDTO.getMessage());
        notification.setUpdatedAt(LocalDateTime.now());
    }
}
