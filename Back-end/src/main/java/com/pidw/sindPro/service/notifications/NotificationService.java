package com.pidw.sindPro.service.notifications;

import com.pidw.sindPro.domains.notifications.Announcement;
import com.pidw.sindPro.dtos.notifications.AnnouncementDTO;
import com.pidw.sindPro.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Transactional
    public AnnouncementDTO create(AnnouncementDTO announcementDTO) {
        Announcement announcement = new Announcement();
        copyDtoToEntity(announcementDTO, announcement);
        announcement = announcementRepository.save(announcement);
        return new AnnouncementDTO(announcement);
    }

    @Transactional(readOnly = true)
    public AnnouncementDTO findById(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow();
        return new AnnouncementDTO(announcement);
    }

    @Transactional(readOnly = true)
    public Page<AnnouncementDTO> findAll(Pageable pageable) {
        Page<Announcement> result = announcementRepository.findAll(pageable);
        return result.map(AnnouncementDTO::new);
    }

    @Transactional
    public AnnouncementDTO update(AnnouncementDTO announcementDTO, Long id) {
        Announcement announcement = announcementRepository.getReferenceById(id);
        updateEntity(announcementDTO, announcement);
        announcement = announcementRepository.save(announcement);
        return new AnnouncementDTO(announcement);
    }

    @Transactional
    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }

    private void copyDtoToEntity(AnnouncementDTO announcementDTO, Announcement announcement) {
        announcement.setTitle(announcementDTO.getTitle());
        announcement.setMessage(announcementDTO.getMessage());
        announcement.setSent(LocalDateTime.now());
        announcement.setCreatedAt(LocalDateTime.now());
        announcement.setUpdatedAt(LocalDateTime.now());
    }

    private void updateEntity(AnnouncementDTO announcementDTO, Announcement announcement) {
        announcement.setTitle(announcementDTO.getTitle());
        announcement.setMessage(announcementDTO.getMessage());
        announcement.setUpdatedAt(LocalDateTime.now());
    }
}
