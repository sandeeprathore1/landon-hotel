package com.frankmoley.lil.landon_hotel;

import com.frankmoley.lil.landon_hotel.data.entity.Guest;
import com.frankmoley.lil.landon_hotel.data.repository.GuestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Component
public class CLRunner2 implements CommandLineRunner {

    private final GuestRepository guestRepository;

    public CLRunner2( GuestRepository guestRepository) {this.guestRepository = guestRepository;}

    @Override
    public void run(String... args) throws Exception {

        List<Guest> guests = this.guestRepository.findAll();

        Optional<Guest> guest1=this.guestRepository.findByEmailAddress("aalvarez1y@mlb.com");

        Optional<Guest> guest2=this.guestRepository.findByPhoneNumber("1-(235)314-9823");

//        System.out.println(guest1);
//        System.out.println(guest2);
        // guests.forEach(System.out::println);
    }
}
