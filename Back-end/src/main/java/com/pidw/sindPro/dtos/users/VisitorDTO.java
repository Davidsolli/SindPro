package com.pidw.sindPro.dtos.users;

import com.pidw.sindPro.domains.users.Visitor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VisitorDTO {

    private Long id;
    private String name;
    private String document;
    private Integer apartment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    public VisitorDTO(Visitor visitor) {
        id = visitor.getId();
        name = visitor.getName();
        document = visitor.getDocument();
        apartment = visitor.getUser().getApartment();
        createdAt = visitor.getCreatedAt();
        updatedAt = visitor.getUpdatedAt();
        userId = visitor.getUser().getId();
    }
}
