package com.pidw.sindPro.repositories;

import com.pidw.sindPro.domains.notifications.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningRepository extends JpaRepository<Warning, Long> {
}