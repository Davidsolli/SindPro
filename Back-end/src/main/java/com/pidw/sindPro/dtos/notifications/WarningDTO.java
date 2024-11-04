package com.pidw.sindPro.dtos.notifications;

import com.pidw.sindPro.domains.notifications.Warning;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WarningDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WarningDTO(Warning warning) {
        id = warning.getId();
        title = warning.getTitle();
        message = warning.getMessage();
        createdAt = warning.getCreatedAt();
        updatedAt = warning.getUpdatedAt();
    }
}
