package com.e1000son.flightreservation.services;

import com.e1000son.flightreservation.dto.ReservationRequest;
import com.e1000son.flightreservation.entities.Flight;
import com.e1000son.flightreservation.entities.Passenger;
import com.e1000son.flightreservation.entities.Reservation;
import com.e1000son.flightreservation.repos.IFlightRepository;
import com.e1000son.flightreservation.repos.IPassengerRepository;
import com.e1000son.flightreservation.repos.IReservationRepository;
import com.e1000son.flightreservation.util.EmailUtil;
import com.e1000son.flightreservation.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservationServiceImpl implements IReservationService{
    @Autowired
    private IFlightRepository flightRepository;
    @Autowired
    private IPassengerRepository passengerRepository;
    @Autowired
    private IReservationRepository reservationRepository;
    @Autowired
    private PDFGenerator pdfGenerator;
    @Autowired
    private EmailUtil emailUtil;
    @Override
    public Reservation bookFlight(ReservationRequest reservationRequest) {

        //In reality the payment would be done here
        //reservationRequest.getCardNumber();
        //Exception handling...
        // (...)

        Long fligthId = reservationRequest.getFlightId();
        Flight flight = flightRepository.findById(fligthId).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setMiddleName(reservationRequest.getPassengerMiddleName());
        passenger.setEmail(reservationRequest.getPassengerEmail());
        passenger.setPhone(reservationRequest.getPassengerPhone());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        reservation.setCreated(new Date());
        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = "/Users/e1000son/Documents/reserva" + savedReservation.getId() + ".pdf";
        pdfGenerator.generateItinerary(savedReservation, filePath);
        emailUtil.sendItinerary(passenger.getEmail(), filePath);

        return savedReservation;
    }
}
