package com.e1000son.flightreservation.services;

import com.e1000son.flightreservation.dto.ReservationRequest;
import com.e1000son.flightreservation.entities.Reservation;

public interface IReservationService {

    public Reservation bookFlight(ReservationRequest reservationRequest);
}
