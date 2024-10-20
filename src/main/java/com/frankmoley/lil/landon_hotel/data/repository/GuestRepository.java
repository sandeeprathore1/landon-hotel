package com.frankmoley.lil.landon_hotel.data.repository;

import com.frankmoley.lil.landon_hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findAllById(long id);

    Optional<Guest> findByEmailAddress(String emailAddress);

    Optional<Guest> findByPhoneNumber(String phoneNumber);

}
