package com.e1000son.flightreservation.controllers;

import com.e1000son.flightreservation.dto.ReservationRequest;
import com.e1000son.flightreservation.entities.Flight;
import com.e1000son.flightreservation.entities.Reservation;
import com.e1000son.flightreservation.repos.IFlightRepository;
import com.e1000son.flightreservation.services.ReservationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    @Autowired
    private IFlightRepository flightRepository;
    @Autowired
    private ReservationServiceImpl reservationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
        LOGGER.info("Inside showCompleteReservation(). Invoked with the flight ID: " + flightId);
        Flight flight = flightRepository.findById(flightId).get();
        modelMap.addAttribute("flight", flight);
        LOGGER.info("Flight found are: " + flight);
        return "completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request, ModelMap modelMap){
        LOGGER.info("Inside completeReservation() " + request);

        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg", "Reservation created successifully (ID: " +
                reservation.getId() + ")");

        return "reservationConfirmation";
    }
}
