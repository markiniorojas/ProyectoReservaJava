package com.sena.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.reservation.DTO.mesaDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.model.mesaStatus;
import com.sena.reservation.service.mesaServices;

@RestController
@RequestMapping("api/v1/mesa")
public class mesaController {

    @Autowired
    private mesaServices MesaService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllMesa() {
        var listMesa = MesaService.findAllMesas();
        return new ResponseEntity<>(listMesa, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        var mesa = MesaService.findByIdMesa(id);
        return new ResponseEntity<>(mesa, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
public ResponseEntity<Object> findByStatus(@PathVariable String status) {
    try {
        mesaStatus statusEnum = mesaStatus.valueOf(status.toUpperCase());
        var mesas = MesaService.findByStatus(statusEnum);
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>("Estado inválido", HttpStatus.BAD_REQUEST);
    }
}


    @GetMapping("/min-capacity/{capacity}")
    public ResponseEntity<Object> findByMinCapacity(@PathVariable int capacity) {
        var mesas = MesaService.findByMinCapacity(capacity);
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody mesaDtos mesaDto) {
        boolean saved = MesaService.save(mesaDto);
        if (saved) {
            return new ResponseEntity<>("Mesa registrada correctamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al registrar la mesa", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody mesaDtos mesaDto) {
        try {
            MesaService.update(id, mesaDto);
            return new ResponseEntity<>("Mesa actualizada correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        responseDTO response = MesaService.delete(id);
        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}
