package com.sena.reservation.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.reservation.model.reservation;
import com.sena.reservation.model.reservationStatus;

@Repository
public interface IReservation extends JpaRepository<reservation, Integer> {

    // El JpaRepository incluye
    // SELECT
    // INSERT
    // UPDATE
    // DELETE
    // Por defecto

    @Query("SELECT r FROM reservation r WHERE r.reservationStatus = ?1")
    List<reservation> findAllByStatus(reservationStatus status);

    // Buscar reservas por estado y por cliente (ejemplo adicional)
    @Query("SELECT r FROM reservation r WHERE r.reservationStatus = ?1 AND r.client.id_client = ?2")
    List<reservation> findByStatusAndClient(reservationStatus status, int clientId);

    // Buscar reserva específica con el id si está en estado confirmado
    @Query("SELECT r FROM reservation r WHERE r.id = ?1 AND r.reservationStatus = 'CONFIRMED'")
    Optional<reservation> findConfirmedById(int id);
}
