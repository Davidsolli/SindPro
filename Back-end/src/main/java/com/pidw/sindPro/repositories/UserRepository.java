package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT obj FROM User obj WHERE obj.passwordResetToken = :token")
    Optional<User> findByPasswordResetToken(String token);
}
