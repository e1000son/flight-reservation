package com.e1000son.flightreservation.repos;

import com.e1000son.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightRepository extends JpaRepository<Flight, Long> {
}
