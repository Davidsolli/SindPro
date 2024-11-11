package com.pidw.sindPro.dtos.spaces;

import com.pidw.sindPro.domains.spaces.CommonSpaces;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommonSpacesDTO {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommonSpacesDTO(CommonSpaces commonSpaces) {
        id = commonSpaces.getId();
        name = commonSpaces.getName();
        createdAt = commonSpaces.getCreatedAt();
        updatedAt = commonSpaces.getUpdatedAt();
    }
}
