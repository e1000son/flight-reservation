package com.e1000son.flightreservation.repos;

import com.e1000son.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}