package com.pidw.sindPro.dtos.notifications;

import com.pidw.sindPro.domains.notifications.Announcement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime sent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AnnouncementDTO(Announcement announcement) {
        id = announcement.getId();
        title = announcement.getTitle();
        message = announcement.getMessage();
        sent = announcement.getSent();
        createdAt = announcement.getCreatedAt();
        updatedAt = announcement.getUpdatedAt();
    }
}
