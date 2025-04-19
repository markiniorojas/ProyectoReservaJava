package com.sena.reservation.controller;

import java.util.List;

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

import com.sena.reservation.model.reservation;
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
     public ResponseEntity<Object> findAllReservation(){
            var ListReservation = reservationService.findAllReservation();
            return new ResponseEntity<Object>(ListReservation,HttpStatus.OK);
     }

     @GetMapping("/status/{status}")
     public List<reservation> getByStatus(@PathVariable("status") reservationStatus status) {
     return reservationService.getReservationsByStatus(status);
     }

     @GetMapping("/{id}")
        public ResponseEntity<Object> findByIdReservation(@PathVariable int id) {
            var reservation = reservationService.findByIdReservation(id);
            return new ResponseEntity<>(reservation,HttpStatus.OK);          
     }

    @PostMapping("/")
       public String save(@RequestBody reservation reservation){
           reservationService.save(reservation);
           return "Registro Ok";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody reservation reservation) {
        reservationService.update(id, reservation);
        return "Actualizacion Ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        reservationService.delete(id);
        return "Eliminacion Ok";
    }   

}
