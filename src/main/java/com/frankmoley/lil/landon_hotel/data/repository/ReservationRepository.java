package com.frankmoley.lil.landon_hotel.data.repository;

import com.frankmoley.lil.landon_hotel.data.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByReservationId(Long id);
    List<Reservation> findAllByReservationDate(Date date);
}
