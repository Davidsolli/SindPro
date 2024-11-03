package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.notifications.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT obj FROM Notification obj WHERE obj.receiver.id = :receiverId")
    Page<Notification> findAllNotificationByReceiverId(Pageable pageable, Long receiverId);

}
