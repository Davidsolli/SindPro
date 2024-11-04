package com.pidw.sindPro.domains.notifications;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_warning")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
