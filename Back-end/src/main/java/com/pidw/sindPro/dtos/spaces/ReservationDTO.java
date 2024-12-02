package com.pidw.sindPro.dtos.spaces;

import com.pidw.sindPro.domains.spaces.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    private LocalDate eventDate;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Long spaceId;
    private String spaceName;
    private Long userId;

    public ReservationDTO(Reservation reservation) {
        id = reservation.getId();
        eventDate = reservation.getEventDate();
        createAt = reservation.getCreateAt();
        updatedAt = reservation.getUpdatedAt();
        spaceId = reservation.getSpace().getId();
        spaceName = reservation.getSpace().getName();
        userId = reservation.getUser().getId();
    }
}
