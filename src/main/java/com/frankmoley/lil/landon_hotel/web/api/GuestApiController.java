package com.frankmoley.lil.landon_hotel.web.api;

import com.frankmoley.lil.landon_hotel.data.entity.Guest;
import com.frankmoley.lil.landon_hotel.data.repository.GuestRepository;
import com.frankmoley.lil.landon_hotel.web.exception.BadRequestException;
import com.frankmoley.lil.landon_hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/guests")
public class GuestApiController {
    private final GuestRepository guestRepository;

    public GuestApiController( GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> getAllGuest() {
        return this.guestRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@RequestBody Guest guest) {
        return this.guestRepository.save(guest);
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable Long id) {
        Optional<Guest> guest = this.guestRepository.findById(id);
        if(guest.isEmpty()){
            throw new NotFoundException("Guest not found");
        }
        return guest.get();
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        if(id!=guest.getId()){
            throw new BadRequestException("id mismatch");
        }
        return this.guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGuest(@PathVariable Long id) {
        Optional<Guest> guest = this.guestRepository.findById(id);
        if(guest.isEmpty()){
            throw new NotFoundException("Guest not found");
        }
        this.guestRepository.deleteById(id);
    }


}
