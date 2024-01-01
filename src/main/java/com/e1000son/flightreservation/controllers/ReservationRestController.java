package com.e1000son.flightreservation.controllers;

import com.e1000son.flightreservation.dto.ReservationUpdateRequest;
import com.e1000son.flightreservation.entities.Reservation;
import com.e1000son.flightreservation.repos.IReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
public class ReservationRestController {
    @Autowired
    private IReservationRepository reservationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        LOGGER.info("Inside findReservation(). Invoked with the ID: " + id);
        return reservationRepository.findById(id).get();
    }

    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request){
        LOGGER.info("Inside updateReservation(). Request: " + request);
        Reservation dbReservation = reservationRepository.findById(request.getId()).orElseThrow();
        dbReservation.setNumberOfBags(request.getNumberOfBags());
        dbReservation.setCheckedIn(request.getCheckedIn());
        dbReservation.setCreated(new Date());
        LOGGER.info("Saving reservation ...");
        return reservationRepository.save(dbReservation);
    }
}
