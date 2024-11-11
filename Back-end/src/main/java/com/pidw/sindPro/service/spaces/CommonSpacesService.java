package com.pidw.sindPro.service.spaces;

import com.pidw.sindPro.domains.spaces.CommonSpaces;
import com.pidw.sindPro.dtos.spaces.CommonSpacesDTO;
import com.pidw.sindPro.repositories.CommonSpacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommonSpacesService {
    
    @Autowired
    private CommonSpacesRepository commonSpacesRepository;
    
    @Transactional
    public CommonSpacesDTO create(CommonSpacesDTO commonSpacesDTO) {
        CommonSpaces commonSpaces = new CommonSpaces();
        createEntity(commonSpaces, commonSpacesDTO);
        commonSpaces = commonSpacesRepository.save(commonSpaces);
        return new CommonSpacesDTO(commonSpaces);
    }

    @Transactional(readOnly = true)
    public CommonSpacesDTO findById(Long id) {
        CommonSpaces commonSpaces = commonSpacesRepository.findById(id).orElseThrow();
        return new CommonSpacesDTO(commonSpaces);
    }

    @Transactional(readOnly = true)
    public List<CommonSpacesDTO> findAll() {
        List<CommonSpaces> result = commonSpacesRepository.findAll();
        return result.stream().map(CommonSpacesDTO::new).toList();
    }

    @Transactional
    public CommonSpacesDTO update(CommonSpacesDTO commonSpacesDTO, Long id) {
        CommonSpaces commonSpaces = commonSpacesRepository.getReferenceById(id);
        updateEntity(commonSpaces, commonSpacesDTO);
        return new CommonSpacesDTO(commonSpaces);
    }

    @Transactional
    public void delete(Long id) {
        commonSpacesRepository.deleteById(id);
    }

    private void createEntity(CommonSpaces commonSpaces, CommonSpacesDTO commonSpacesDTO) {
        commonSpaces.setName(commonSpacesDTO.getName());
        commonSpaces.setCreatedAt(LocalDateTime.now());
        commonSpaces.setUpdatedAt(LocalDateTime.now());
    }

    private void updateEntity(CommonSpaces commonSpaces, CommonSpacesDTO commonSpacesDTO) {
        commonSpaces.setName(commonSpacesDTO.getName());
        commonSpaces.setUpdatedAt(LocalDateTime.now());
    }
}
