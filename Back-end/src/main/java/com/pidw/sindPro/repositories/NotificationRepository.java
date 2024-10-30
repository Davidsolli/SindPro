package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.notifications.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
