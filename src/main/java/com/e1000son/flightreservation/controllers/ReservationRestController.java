package com.e1000son.flightreservation.controllers;

import com.e1000son.flightreservation.dto.ReservationUpdateRequest;
import com.e1000son.flightreservation.entities.Reservation;
import com.e1000son.flightreservation.repos.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
        return reservationRepository.save(dbReservation);
    }
}
