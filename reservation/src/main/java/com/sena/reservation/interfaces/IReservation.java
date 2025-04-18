package com.sena.reservation.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.reservation.model.reservation;
import com.sena.reservation.model.reservationStatus;

@Repository
public interface IReservation extends JpaRepository<reservation, Integer> {
    List<reservation> findByReservationStatus(reservationStatus status);
}
