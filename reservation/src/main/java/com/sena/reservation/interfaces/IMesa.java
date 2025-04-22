package com.sena.reservation.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.reservation.model.mesa;
import com.sena.reservation.model.mesaStatus;

@Repository
public interface IMesa extends JpaRepository<mesa, Integer> {
    
    // Corrige el nombre del campo en la consulta
    @Query("SELECT t FROM mesa t WHERE t.tableStatus = ?1")
    List<mesa> findAllByStatus(mesaStatus status);

    @Query("SELECT t FROM mesa t WHERE t.capacity_table >= ?1")
    List<mesa> findByMinCapacity(int minCapacity);
}
