package com.e1000son.flightreservation.repos;

import com.e1000son.flightreservation.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassengerRepository extends JpaRepository<Passenger, Long> {
}
