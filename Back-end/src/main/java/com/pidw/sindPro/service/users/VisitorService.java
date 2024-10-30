package com.pidw.sindPro.service.users;

import com.pidw.sindPro.domains.users.User;
import com.pidw.sindPro.domains.users.Visitor;
import com.pidw.sindPro.dtos.users.VisitorDTO;
import com.pidw.sindPro.repositories.UserRepository;
import com.pidw.sindPro.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public VisitorDTO create(VisitorDTO visitorDTO, Long userId) {
        Visitor visitor = new Visitor();
        createEntity(visitor, visitorDTO);
        visitor.setUser(userRepository.getReferenceById(userId));
        visitor = visitorRepository.save(visitor);
        return new VisitorDTO(visitor);
    }

    @Transactional(readOnly = true)
    public VisitorDTO findById(Long id) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow();
        return new VisitorDTO(visitor);
    }

    @Transactional(readOnly = true)
    public List<VisitorDTO> findAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Visitor> result = visitorRepository.findAllByEmail(user.getEmail());
        return result.stream().map(VisitorDTO::new).toList();
    }
    
    @Transactional
    public VisitorDTO update(Long id, VisitorDTO visitorDTO) {
        Visitor visitor = visitorRepository.getReferenceById(id);
        updateEntity(visitor, visitorDTO);
        return new VisitorDTO(visitor);
    }

    @Transactional
    public void delete(Long id) {
        visitorRepository.deleteById(id);
    }

    private void createEntity(Visitor visitor, VisitorDTO visitorDTO) {
        visitor.setName(visitorDTO.getName());
        visitor.setDocument(visitorDTO.getDocument());
        visitor.setVisitDate(visitorDTO.getVisitDate());
        visitor.setArrived(visitorDTO.getArrived());
        visitor.setExit(visitorDTO.getExit());
        visitor.setCreatedAt(LocalDateTime.now());
        visitor.setUpdatedAt(LocalDateTime.now());
    }

    private void updateEntity(Visitor visitor, VisitorDTO visitorDTO) {
        visitor.setName(visitorDTO.getName());
        visitor.setVisitDate(visitorDTO.getVisitDate());
        visitor.setArrived(visitorDTO.getArrived());
        visitor.setExit(visitorDTO.getExit());
        visitor.setUpdatedAt(LocalDateTime.now());
    }
}
