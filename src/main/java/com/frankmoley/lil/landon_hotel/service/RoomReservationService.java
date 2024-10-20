package com.frankmoley.lil.landon_hotel.service;


import com.frankmoley.lil.landon_hotel.data.entity.Guest;
import com.frankmoley.lil.landon_hotel.data.entity.Reservation;
import com.frankmoley.lil.landon_hotel.data.entity.Room;
import com.frankmoley.lil.landon_hotel.data.repository.GuestRepository;
import com.frankmoley.lil.landon_hotel.data.repository.ReservationRepository;
import com.frankmoley.lil.landon_hotel.data.repository.RoomRepository;
import com.frankmoley.lil.landon_hotel.service.model.RoomReservation;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class RoomReservationService {

    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public RoomReservationService(GuestRepository guestRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(String reservationDate) {
        Date date = null;

        if(StringUtils.isNotEmpty(reservationDate)) {
            date= Date.valueOf(reservationDate);
        }else{
            date = new Date(new java.util.Date().getTime());
        }
        Map<Long, RoomReservation>  roomReservations = new HashMap<>();
        List<Room> rooms = this.roomRepository.findAll();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.put(room.getId(), roomReservation);
        });

        List<Reservation> reservations = this.reservationRepository.findAllByReservationDate(date);

        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getReservationId());
            roomReservation.setReservationDate(reservation.getReservationDate().toString());

            Optional<Guest> guests = this.guestRepository.findAllById(reservation.getGuestId());
            roomReservation.setGuestId(guests.get().getId());
            roomReservation.setFirstName(guests.get().getFirstName());
            roomReservation.setLastName(guests.get().getLastName());
        });

        return roomReservations.values().stream().toList();
    }
}



