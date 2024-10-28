package com.pidw.sindPro.domains.notifications;


import com.pidw.sindPro.domains.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime sent;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
