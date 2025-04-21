package com.sena.reservation.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.reservation.model.TableReservation;

@Repository
public interface ITableReservation extends JpaRepository<TableReservation, Integer> {
    
    // Método para buscar por el ID de la reservación
    // List<TableReservation> findByReservation_Id_reservation(int idReservation);
    // // Método para buscar por el ID de la mesa
    // List<TableReservation> findByMesa_Id_mesa(int idMesa);
    
    // // Método para buscar por ambos IDs
    // List<TableReservation> findByReservation_Id_ReservationAndMesa_Id_Mesa(int idReservation, int idMesa);

}