package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.spaces.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByEventDate(LocalDate eventDate);

    @Query(value = """
            SELECT obj
            FROM Reservation obj
            JOIN FETCH obj.space
            JOIN FETCH obj.user
            """)
    List<Reservation> findAllReservations();
}
