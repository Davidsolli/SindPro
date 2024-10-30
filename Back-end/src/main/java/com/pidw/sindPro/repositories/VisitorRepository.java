package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.users.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    @Query(value = "SELECT obj FROM Visitor obj WHERE obj.user.email = :email")
    List<Visitor> findAllByEmail(String email);
}
