package com.sena.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.reservation.DTO.reservationDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.model.reservationStatus;
import com.sena.reservation.service.reservationServices;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/reservation")
public class reservationController {

    /*
     * Get: obtener datos o consultar
     * Post: registrar y crear datos
     * Put: Actualizar datos de todo el objeto
     * Patch: Actualizacion parcial
     * Delete: Eliminar objetos
     */

     @Autowired
     private reservationServices reservationService;

     @GetMapping("/")
     public ResponseEntity<Object> findAllReservations(){
            var listReservation = reservationService.findAllReservations();
            return new ResponseEntity<>(listReservation, HttpStatus.OK);
     }

     @GetMapping("/status/{status}")
     public ResponseEntity<Object> getReservationsByStatus(@PathVariable("status") reservationStatus status) {
        var listReservation = reservationService.getReservationsByStatus(status);
        return new ResponseEntity<>(listReservation, HttpStatus.OK);
     }

     @GetMapping("/{id}")
     public ResponseEntity<Object> findByIdReservation(@PathVariable int id) {
         var reservation = reservationService.findByIdReservation(id);
         return new ResponseEntity<>(reservation, HttpStatus.OK);          
     }

     @PostMapping("/")
     public ResponseEntity<String> save(@RequestBody reservationDtos reservationDto){
         boolean saved = reservationService.save(reservationDto);
         if (saved) {
             return new ResponseEntity<>("Registro Ok", HttpStatus.CREATED);
         } else {
             return new ResponseEntity<>("Error: El cliente con ID " + reservationDto.getId_client() + " no existe o los datos son inválidos", HttpStatus.BAD_REQUEST);
         }
     }

     @PutMapping("/{id}")
     public ResponseEntity<String> update(@PathVariable int id, @RequestBody reservationDtos reservationDto) {
         try {
             reservationService.update(id, reservationDto);
             return new ResponseEntity<>("Actualización Ok", HttpStatus.OK);
         } catch (RuntimeException e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Object> delete(@PathVariable int id) {
         responseDTO response = reservationService.delete(id);
         return new ResponseEntity<>(response.getMessage(), response.getStatus());
     }
     
     @PutMapping("/restore/{id}")
     public ResponseEntity<Object> restore(@PathVariable int id){
         responseDTO response = reservationService.restore(id);
         return new ResponseEntity<>(response.getMessage(), response.getStatus());
     }
}