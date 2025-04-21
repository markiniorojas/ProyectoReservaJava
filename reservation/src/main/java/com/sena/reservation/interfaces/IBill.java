package com.sena.reservation.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.reservation.model.bill;
import com.sena.reservation.model.billStatus;

@Repository
public interface IBill extends JpaRepository<bill, Integer> {
    // El JpaRepository incluye
    // SELECT
    // INSERT
    // UPDATE
    // DELETE
    // Por defecto

    // Ejemplo: Buscar facturas por estado de pago
    @Query("SELECT b FROM bill b WHERE b.billStatus LIKE %?1%")
    List<bill> findAllByBillStatus(billStatus status);


    // Ejemplo: Buscar una factura por el ID de la reserva asociada
    @Query("SELECT b FROM bill b WHERE b.total_price = ?1")
    Optional<bill> findByTotalPrice(double totalPrice);// Asumiendo que el ID de Reservation es Long
}
