package com.sena.reservation.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.reservation.DTO.reservationDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.interfaces.IClient;
import com.sena.reservation.interfaces.IReservation;
import com.sena.reservation.model.client;
import com.sena.reservation.model.reservation;
import com.sena.reservation.model.reservationStatus;

@Service
public class reservationServices {

    @Autowired
    private IReservation reservationData;

    @Autowired
    private IClient clientData; // Para verificar si el cliente existe

    // Obtener todas las reservas
    public List<reservation> findAllReservations() {
        return reservationData.findAll();   
    }

    // Obtener reservas por estado
    public List<reservation> getReservationsByStatus(reservationStatus status) {
        return reservationData.findAllByStatus(status);
    }

    // Obtener una reserva por ID
    public Optional<reservation> findByIdReservation(int id) {
        return reservationData.findById(id);
    }

    // Método para verificar si un cliente existe
    public boolean existsClientById(int clientId) {
        return clientData.existsById(clientId);
    }

    // Método para guardar una reserva
    public boolean save(reservationDtos reservationDto) {
        int clientId = reservationDto.getId_client(); 
        if (existsClientById(clientId)) {
            reservation newReservation = converRegisterToReservation(reservationDto);
            if (newReservation != null) {
                reservationData.save(newReservation);
                return true;
            }
        }
        return false; 
    }

    // Convertir DTO a entidad reservation
        // Convertir DTO a entidad reservation
public reservation converRegisterToReservation(reservationDtos reservationDto) { 
    Optional<client> clientEntity = clientData.findById(reservationDto.getId_client());
    
    // Si el cliente existe, crear y devolver la reserva
    if(clientEntity.isPresent()) {
        try {
            LocalDate date = LocalDate.parse(reservationDto.getReservationdate());
            LocalTime time = LocalTime.parse(reservationDto.getReservationtime());
            
            // Convertir el String a enum reservationStatus
            reservationStatus status = reservationStatus.valueOf(reservationDto.getReservationStatus().toUpperCase());
            
            return new reservation(
                0, // ID auto-generado
                clientEntity.get(),
                date,
                time,
                reservationDto.getQuantityPersons(),
                status
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir los datos: " + e.getMessage());
        }
    }
    // Si el cliente no existe, retornar null
    return null;
    }

    // Actualizar una reserva
public void update(int id, reservationDtos reservationDto) {
    var existingReservation = findByIdReservation(id);
    if (existingReservation.isPresent()) {
        var reservation = existingReservation.get();

        int clientId = reservationDto.getId_client();
        Optional<client> clientEntity = clientData.findById(clientId);

        if (clientEntity.isEmpty()) {
            throw new RuntimeException("El cliente con ID " + clientId + " no existe.");
        }

        try {
            // Convertir String a LocalDate y LocalTime
            LocalDate date = LocalDate.parse(reservationDto.getReservationdate());
            LocalTime time = LocalTime.parse(reservationDto.getReservationtime());
            
            // Convertir String a enum reservationStatus
            reservationStatus status = reservationStatus.valueOf(reservationDto.getReservationStatus().toUpperCase());

            // Si el cliente existe, actualizamos los datos de la reserva
            reservation.setClient(clientEntity.get());
            reservation.setReservationdate(date);
            reservation.setReservationtime(time);
            reservation.setQuantityPersons(reservationDto.getQuantityPersons());
            reservation.setReservationStatus(status);

            reservationData.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la reserva: " + e.getMessage());
        }
    } else {
        throw new RuntimeException("La reserva con ID " + id + " no existe.");
    }
}
    

    // Eliminar una reserva (en este caso, marcamos como eliminada)
    public responseDTO delete(int id) {
        var reservation = findByIdReservation(id);
        responseDTO response = new responseDTO();  
        if(reservation.isPresent()){
            reservationData.delete(reservation.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Reserva eliminada con éxito");
            return response;
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Reserva no encontrada");
            return response;
        }
    }

    // Restaurar una reserva eliminada
    public responseDTO restore(int id) {
        var reservation = reservationData.findById(id);
        responseDTO response = new responseDTO();
        
        if (reservation.isPresent()) {
            response.setStatus(HttpStatus.OK);
            response.setMessage("Reserva restaurada con éxito");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Reserva no encontrada");
        }
        return response;
    }
}
