package com.pidw.sindPro.domains.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate visitDate;
    private LocalTime arrived;
    private LocalTime exit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
