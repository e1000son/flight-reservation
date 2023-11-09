package com.e1000son.flightreservation.controllers;

import com.e1000son.flightreservation.entities.Flight;
import com.e1000son.flightreservation.repos.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {
    @Autowired
    private IFlightRepository flightRepository;
    @RequestMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
                              @RequestParam("departureDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date departureDate,
                              ModelMap modelMap){
        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
        modelMap.addAttribute("flights", flights);

        return "displayFlights";
    }
}
