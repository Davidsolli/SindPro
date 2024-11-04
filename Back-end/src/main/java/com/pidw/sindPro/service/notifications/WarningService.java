package com.pidw.sindPro.service.notifications;

import com.pidw.sindPro.domains.notifications.Warning;
import com.pidw.sindPro.dtos.notifications.WarningDTO;
import com.pidw.sindPro.repositories.WarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarningService {

    @Autowired
    private WarningRepository warningRepository;

    @Transactional
    public WarningDTO create(WarningDTO warningDTO) {
        Warning warning = new Warning();
        createEntity(warning, warningDTO);
        warning = warningRepository.save(warning);
        return new WarningDTO(warning);
    }

    @Transactional(readOnly = true)
    public List<WarningDTO> findAll() {
        List<Warning> result = warningRepository.findAll();
        return result.stream().map(WarningDTO::new).toList();
    }

    @Transactional
    public WarningDTO update(Long id, WarningDTO warningDTO) {
        Warning warning = warningRepository.getReferenceById(id);
        updateEntity(warning, warningDTO);
        warning = warningRepository.save(warning);
        return new WarningDTO(warning);
    }

    @Transactional
    public void delete(Long id) {
        warningRepository.deleteById(id);
    }

    private void updateEntity(Warning warning, WarningDTO warningDTO) {
        warning.setTitle(warningDTO.getTitle());
        warning.setMessage(warningDTO.getMessage());
        warning.setUpdatedAt(LocalDateTime.now());
    }

    private void createEntity(Warning warning, WarningDTO warningDTO) {
        warning.setTitle(warningDTO.getTitle());
        warning.setMessage(warningDTO.getMessage());
        warning.setCreatedAt(LocalDateTime.now());
        warning.setUpdatedAt(LocalDateTime.now());
    }
}
