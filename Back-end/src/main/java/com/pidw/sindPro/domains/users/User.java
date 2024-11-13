package com.pidw.sindPro.domains.users;

import com.pidw.sindPro.domains.notifications.Notification;
import com.pidw.sindPro.domains.spaces.Reservation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String cpf;
    private String address;
    private Integer Apartment;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String passwordResetToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "sender")
    private List<Notification> notificationsSent = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Notification> notificationsReceived = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Visitor> visitors = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.ADMIN) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_RESIDENT"),
                new SimpleGrantedAuthority("ROLE_DOORMAN")
        );
        else if (this.userRole == UserRole.RESIDENT) return List.of(new SimpleGrantedAuthority("ROLE_RESIDENT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_DOORMAN"));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
