package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.reservation.DTO.mesaDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.interfaces.IMesa;
import com.sena.reservation.model.mesa;
import com.sena.reservation.model.mesaStatus;

@Service
public class mesaServices {

    @Autowired
    private IMesa MesaData;

    public List<mesa> findAllMesas() {
        return MesaData.findAll();
    }

    public List<mesa> findByStatus(mesaStatus status) {
        return MesaData.findAllByStatus(status);
    }

    public List<mesa> findByMinCapacity(int minCapacity) {
        return MesaData.findByMinCapacity(minCapacity);
    }

    public Optional<mesa> findByIdMesa(int id) {
        return MesaData.findById(id);
    }

    public boolean save(mesaDtos mesaDto) {
        mesa mesaNueva = convertDtoToMesa(mesaDto);
        if (mesaNueva != null) {
            MesaData.save(mesaNueva);
            return true;
        }
        return false;
    }

    public mesa convertDtoToMesa(mesaDtos mesaDto) {
        try {
            mesaStatus status = mesaStatus.valueOf(mesaDto.getTableStatus().toUpperCase());
            return new mesa(
                0,
                mesaDto.getCapacityTable(),
                status
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir mesaDto: " + e.getMessage());
        }
    }

    public void update(int id, mesaDtos mesaDto) {
        Optional<mesa> mesaExistente = findByIdMesa(id);
        if (mesaExistente.isPresent()) {
            mesa mesa = mesaExistente.get();
            try {
                mesaStatus status = mesaStatus.valueOf(mesaDto.getTableStatus().toUpperCase());
                mesa.setCapacity_table(mesaDto.getCapacityTable());
                mesa.setTableStatus(status);
                MesaData.save(mesa);
            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar mesa: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("La mesa con ID " + id + " no existe.");
        }
    }

    public responseDTO delete(int id) {
        Optional<mesa> mesa = findByIdMesa(id);
        responseDTO response = new responseDTO();
        if (mesa.isPresent()) {
            MesaData.delete(mesa.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Mesa eliminada con éxito");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Mesa no encontrada");
        }
        return response;
    }
}
