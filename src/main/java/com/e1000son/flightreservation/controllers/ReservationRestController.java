package com.e1000son.flightreservation.controllers;

import com.e1000son.flightreservation.dto.ReservationUpdateRequest;
import com.e1000son.flightreservation.entities.Reservation;
import com.e1000son.flightreservation.repos.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
public class ReservationRestController {
    @Autowired
    private IReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        return reservationRepository.findById(id).get();
    }

    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request){
        Reservation dbReservation = reservationRepository.findById(request.getId()).orElseThrow();
        dbReservation.setNumberOfBags(request.getNumberOfBags());
        dbReservation.setCheckedIn(request.getCheckedIn());
        dbReservation.setCreated(new Date());
        return reservationRepository.save(dbReservation);
    }
}
