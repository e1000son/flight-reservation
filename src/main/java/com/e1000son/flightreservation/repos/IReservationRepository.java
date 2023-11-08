package com.e1000son.flightreservation.repos;

import com.e1000son.flightreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
