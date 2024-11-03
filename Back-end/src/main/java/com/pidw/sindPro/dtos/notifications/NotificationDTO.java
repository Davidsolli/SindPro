package com.pidw.sindPro.dtos.notifications;

import com.pidw.sindPro.domains.notifications.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long receiverId;

    public NotificationDTO(Notification notification) {
        id = notification.getId();
        title = notification.getTitle();
        message = notification.getMessage();
        receiverId = notification.getReceiver().getId();
        createdAt = notification.getCreatedAt();
        updatedAt = notification.getUpdatedAt();
    }
}
