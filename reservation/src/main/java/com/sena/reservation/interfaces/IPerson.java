package com.sena.reservation.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.reservation.model.person;

@Repository
public interface IPerson extends JpaRepository<person, Integer>{

    // El JpaRepository incluye
    // SELECT
    // INSERT
    // UPDATE
    // DELETE
    // Por defecto
    @Query("SELECT p FROM person p WHERE p.name LIKE %?1%")
    List<person> filterForName(String filter);

    // Nuevo método para encontrar todas las personas activas
    @Query("SELECT p FROM person p WHERE p.active = true")
    List<person> findAllActive();

    // Método para encontrar una persona activa por ID
    @Query("SELECT p FROM person p WHERE p.id_person = ?1 AND p.active = true")
    Optional<person> findActiveById(int id);

    @Query("UPDATE person p SET p.active = true WHERE p.id_person = ?1")
    void restore(int id);
} 
