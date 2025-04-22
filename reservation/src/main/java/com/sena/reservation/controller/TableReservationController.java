package com.sena.reservation.controller;

import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.DTO.tablaReservationDtos;
import com.sena.reservation.service.tableReservationServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tableReservation")
public class TableReservationController {

    @Autowired
    private tableReservationServices service;

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var list = service.findAllTableReservation();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        var reservation = service.findByIdTableReservation(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody tablaReservationDtos TableReservation) {
        service.save(TableReservation);
        return "Registro de TableReservation exitoso";
    }

    @PutMapping("/")
    public String update(@RequestBody tablaReservationDtos dto) {
        service.update(dto);
        return "Actualización OK";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        responseDTO response = service.delete(id);
        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }

    // Extra: Buscar por id_reservation
    // @GetMapping("/reservation/{idReservation}")
    // public ResponseEntity<Object> findByReservation(@PathVariable int idReservation) {
    //     var result = service.findByReservationIdReservation(idReservation);
    //     return new ResponseEntity<>(result, HttpStatus.OK);
    // }

    // // Extra: Buscar por id_mesa
    // @GetMapping("/mesa/{idMesa}")
    // public ResponseEntity<Object> findByMesa(@PathVariable int idMesa) {
    //     var result = service.findByMesaIdMesa(idMesa);
    //     return new ResponseEntity<>(result, HttpStatus.OK);
    // }

    // // Extra: Buscar por ambos
    // @GetMapping("/buscar/{idReservation}/{idMesa}")
    // public ResponseEntity<Object> findByReservationAndMesa(@PathVariable int idReservation, @PathVariable int idMesa) {
    //     var result = service.findByReservationIdReservationAndMesaIdMesa(idReservation, idMesa);
    //     return new ResponseEntity<>(result, HttpStatus.OK);
    // }
}
