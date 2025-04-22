package com.sena.reservation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.DTO.tablaReservationDtos;
import com.sena.reservation.interfaces.IMesa;
import com.sena.reservation.interfaces.IReservation;
import com.sena.reservation.interfaces.ITableReservation;

import com.sena.reservation.model.TableReservation;
import com.sena.reservation.model.reservation;
import com.sena.reservation.model.mesa;

@Service
public class tableReservationServices {

    @Autowired
    private ITableReservation tableReservationRepo;

    @Autowired
    private IReservation reservationData;


    @Autowired
    private IMesa mesaData;

    public List<tablaReservationDtos> findAllTableReservation(){
    List<TableReservation> entities = tableReservationRepo.findAll();
    List<tablaReservationDtos> dtos = new ArrayList<>();
    
    for (TableReservation tableReservation : entities) {
        tablaReservationDtos dto = new tablaReservationDtos();
        dto.setId_tableReservation(tableReservation.getReservation().getId_reservation());
        dto.setId_reservation(tableReservation.getReservation().getId_reservation());
        dto.setId_mesa(tableReservation.getMesa().getId_mesa());
        
        dtos.add(dto);
    }
    
    return dtos;
    }

    public Optional<tablaReservationDtos> findByIdTableReservation(int id){
        Optional<TableReservation> optionalEntity = tableReservationRepo.findById(id);
        
        if (optionalEntity.isPresent()) {
            TableReservation tableReservation = optionalEntity.get();
            tablaReservationDtos dto = new tablaReservationDtos();
            dto.setId_tableReservation(tableReservation.getReservation().getId_reservation());
            dto.setId_reservation(tableReservation.getReservation().getId_reservation());
            dto.setId_mesa(tableReservation.getMesa().getId_mesa());
            
            return Optional.of(dto);
        }
        
        return Optional.empty();
    }


    public void save(tablaReservationDtos TableReservation){
        tableReservationRepo.save(convertRegisterTable(TableReservation));
    }
    
    public TableReservation convertRegisterTable(tablaReservationDtos TableReservation) {
        // Buscar la reserva real por ID
        Optional<reservation> reservationOptional = reservationData.findById(TableReservation.getId_reservation());
        if (!reservationOptional.isPresent()) {
            throw new RuntimeException("La reserva con ID " + TableReservation.getId_reservation() + " no existe");
        }
    
        // Buscar la mesa real por ID
        Optional<mesa> mesaOptional = mesaData.findById(TableReservation.getId_mesa());
        if (!mesaOptional.isPresent()) {
            throw new RuntimeException("La mesa con ID " + TableReservation.getId_mesa() + " no existe");
        }
    
        return new TableReservation(
            0, // ID autogenerado
            reservationOptional.get(),
            mesaOptional.get()
        );
    }
    

    public void update(tablaReservationDtos tableReservationUpdate) {
        Optional<TableReservation> tableReservationOptional = tableReservationRepo
                .findById(tableReservationUpdate.getId_tableReservation());
    
        if (tableReservationOptional.isPresent()) {
            TableReservation tableReservation = tableReservationOptional.get();
    
            // Validar y setear reserva
            if (tableReservationUpdate.getId_reservation() != 0) {
                Optional<reservation> optionalReservation = reservationData.findById(tableReservationUpdate.getId_reservation());
                if (optionalReservation.isPresent()) {
                    tableReservation.setReservation(optionalReservation.get());
                } else {
                    throw new RuntimeException("La reserva con ID " + tableReservationUpdate.getId_reservation() + " no existe");
                }
            }
    
            // Validar y setear mesa
            if (tableReservationUpdate.getId_mesa() != 0) {
                Optional<mesa> optionalMesa = mesaData.findById(tableReservationUpdate.getId_mesa());
                if (optionalMesa.isPresent()) {
                    tableReservation.setMesa(optionalMesa.get());
                } else {
                    throw new RuntimeException("La mesa con ID " + tableReservationUpdate.getId_mesa() + " no existe");
                }
            }
    
            tableReservationRepo.save(tableReservation);
        }
    }
    

    public responseDTO delete(int id) {
        Optional<TableReservation> existing = tableReservationRepo.findById(id);
        responseDTO response = new responseDTO();

        if (existing.isPresent()) {
            tableReservationRepo.delete(existing.get());
            response.setStatus(HttpStatus.OK);  
            response.setMessage("Relación mesa-reserva eliminada con éxito");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Relación no encontrada");
        }

        return response;
    }

    // Métodos adicionales de búsqueda
    // public List<TableReservation> findByReservationIdReservation(int id_Reservation) {
    //     return tableReservationRepo.findByReservation_Id_reservation(id_Reservation);
    // }

    // public List<TableReservation> findByMesaIdMesa(int id_Mesa) {
    //     return tableReservationRepo.findByMesa_Id_mesa(id_Mesa);
    }

    // public List<TableReservation> findByReservationIdReservationAndMesaIdMesa(int id_Reservation, int id_Mesa) {
    //     return tableReservationRepo.findByReservation_Id_ReservationAndMesa_Id_Mesa(id_Reservation, id_Mesa);
    // }

