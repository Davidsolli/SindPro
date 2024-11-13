package com.pidw.sindPro.service.spaces;

import com.pidw.sindPro.domains.spaces.Reservation;
import com.pidw.sindPro.dtos.spaces.ReservationDTO;
import com.pidw.sindPro.repositories.CommonSpacesRepository;
import com.pidw.sindPro.repositories.ReservationRepository;
import com.pidw.sindPro.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonSpacesRepository commonSpacesRepository;

    @Transactional
    public ReservationDTO create(ReservationDTO reservationDTO) {
        boolean dateConflict = reservationRepository.existsByEventDate(reservationDTO.getEventDate());

        if (dateConflict) {
            throw new IllegalArgumentException("Já existe uma reserva para esta data.");
        }

        Reservation reservation = new Reservation();
        createEntity(reservation, reservationDTO);
        reservation = reservationRepository.save(reservation);
        return new ReservationDTO(reservation);
    }

    @Transactional(readOnly = true)
    public ReservationDTO findById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return new ReservationDTO(reservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationDTO> findAll() {
        List<Reservation> result = reservationRepository.findAllReservations();
        return result.stream().map(ReservationDTO::new).toList();
    }

    @Transactional
    public ReservationDTO update(Long id, ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        updateEntity(reservation, reservationDTO);
        reservation = reservationRepository.save(reservation);
        return new ReservationDTO(reservation);
    }

    @Transactional
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    private void createEntity(Reservation reservation, ReservationDTO reservationDTO) {
        reservation.setSpace(commonSpacesRepository.getReferenceById(reservationDTO.getSpaceId()));
        reservation.setEventDate(reservationDTO.getEventDate());
        reservation.setUser(userService.getAuthenticatedUser());
        reservation.setCreateAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());
    }

    private void updateEntity(Reservation reservation, ReservationDTO reservationDTO) {
        reservation.setEventDate(reservationDTO.getEventDate());
        reservation.setUpdatedAt(LocalDateTime.now());
    }

}
