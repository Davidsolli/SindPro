package com.pidw.sindPro.dtos.users;

import com.pidw.sindPro.domains.users.Visitor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class VisitorDTO {

    private Long id;
    private String name;
    private String document;
    private LocalDate visitDate;
    private LocalTime arrived;
    private LocalTime exit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    public VisitorDTO(Visitor visitor) {
        id = visitor.getId();
        name = visitor.getName();
        document = visitor.getDocument();
        visitDate = visitor.getVisitDate();
        arrived = visitor.getArrived();
        exit = visitor.getExit();
        createdAt = visitor.getCreatedAt();
        updatedAt = visitor.getUpdatedAt();
        userId = visitor.getUser().getId();
    }
}
