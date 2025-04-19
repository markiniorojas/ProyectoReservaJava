package com.sena.reservation.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.reservation.model.client;

@Repository
public interface IClient extends JpaRepository<client, Integer> {
    // El JpaRepository incluye
    // SELECT
    // INSERT
    // UPDATE
    // DELETE
    // Por defecto
    @Query("SELECT c FROM client c WHERE c.Username LIKE %?1%")
    List<client> filterForUserName(String filter);

    //método para encontrar todas las personas activas
    @Query("SELECT c FROM client c WHERE c.active = true")
    List<client> findAllActive();

    // Método para encontrar una persona activa por ID
    @Query("SELECT c FROM client c WHERE c.id_person = ?1 AND c.active = true")
    Optional<client> findActiveById(int id);

    @Query("UPDATE client c SET c.active = true WHERE c.id_client = ?1")
    void restore(int id);
}