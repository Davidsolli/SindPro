package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.notifications.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
