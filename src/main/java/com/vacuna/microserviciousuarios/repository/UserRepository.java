package com.vacuna.microserviciousuarios.repository;

import com.vacuna.microserviciousuarios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u WHERE u.rut = ?1")
    Optional<User> findByRut(String rut);

    @Query("SELECT COUNT (u) FROM User u WHERE u.role = false")
    int getCountOfVaccinator();
}
