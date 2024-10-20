package com.frankmoley.lil.landon_hotel;

import com.frankmoley.lil.landon_hotel.data.entity.Reservation;
import com.frankmoley.lil.landon_hotel.data.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CLRunner3 implements CommandLineRunner {

    private final ReservationRepository reservationRepository;

    public CLRunner3(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Optional<Reservation> reservation = reservationRepository.findByReservationId(1L)
        List<Reservation> reservations = this.reservationRepository.findAll();
//       reservations.forEach(System.out::println);

    }
}
