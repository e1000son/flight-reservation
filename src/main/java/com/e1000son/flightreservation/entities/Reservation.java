package com.e1000son.flightreservation.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reservation extends AbstractEntity{
    @Column(name = "CHECKED_IN")
    private Boolean checkedIn;
    @Column(name = "NUMBER_OF_BAGS")
    private int numberOfBags;
    private Date created;
    @OneToOne
    private Flight flight;
    @OneToOne
    private Passenger passenger;

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                ", checkedIn=" + checkedIn +
                ", numberOfBags=" + numberOfBags +
                ", created=" + created +
                ", flight=" + flight +
                ", passenger=" + passenger +
                '}';
    }
}
