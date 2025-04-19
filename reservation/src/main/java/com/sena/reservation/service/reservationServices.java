package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.reservation.interfaces.IReservation;
import com.sena.reservation.model.reservation;
import com.sena.reservation.model.reservationStatus;

@Service
public class reservationServices {

    /**
     * @Autowired = incluye la conexion de la interface
     */
    @Autowired
        private IReservation reservationData;

        public List<reservation> findAllReservation(){
            return reservationData.findAll();
        }
        
        public List<reservation> getReservationsByStatus(reservationStatus status) {
            return reservationData.findByReservationStatus(status);
        }
        

        public Optional<reservation> findByIdReservation(int id){
            return reservationData.findById(id);
        }

        public void save(reservation reservation){
            reservationData.save(reservation);
        }

        public void update(int id, reservation reservationUpdate){
            var reservation = findByIdReservation(id);
            if(reservation.isPresent()){
                reservation.get().setClient(reservationUpdate.getClient());
                reservation.get().setResevationdate(reservationUpdate.getResevationdate());
                reservation.get().setReservationtime(reservationUpdate.getReservationtime());
                reservation.get().setQuantityPersons(reservationUpdate.getQuantityPersons());
                reservation.get().setReservationStatus(reservationUpdate.getReservationStatus()); // Añadido
                reservationData.save(reservation.get());
            }
        }

        public void delete(int id){
            var reservation = findByIdReservation(id);
            if(reservation.isPresent()){
                reservationData.delete(reservation.get());
            }
        }
}
