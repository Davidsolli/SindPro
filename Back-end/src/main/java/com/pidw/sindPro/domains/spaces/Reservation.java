package com.pidw.sindPro.domains.spaces;

import com.pidw.sindPro.domains.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate eventDate;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private CommonSpaces space;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getApartment() {
        return user != null ? user.getApartment() : null;
    }
}
