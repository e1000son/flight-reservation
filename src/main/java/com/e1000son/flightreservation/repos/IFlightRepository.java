package com.e1000son.flightreservation.repos;

import com.e1000son.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IFlightRepository extends JpaRepository<Flight, Long> {
    @Query("from Flight f where f.departureCity = :departureCity AND f.arrivalCity = :arrivalCity AND f.dateOfDeparture = :dateOfDeparture")
    List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to, @Param("dateOfDeparture") Date departureDate);
}
