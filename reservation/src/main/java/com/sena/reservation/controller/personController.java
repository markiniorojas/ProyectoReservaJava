package com.sena.reservation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.reservation.DTO.PersonDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.service.personServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/person")
public class personController {

    /*
     * Get: obtener datos o consultar
     * Post: registrar y crear datos
     * Put: Actualizar datos de todo el objeto
     * Patch: Actualizacion parcial
     * Delete: Eliminar objetos
     */

    @Autowired
    private personServices PersonService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllPerson() {
        var ListPerson = PersonService.findAllPerson();
        return new ResponseEntity<Object>(ListPerson,HttpStatus.OK);
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> filterForName(@PathVariable String name) {
        var ListPerson = PersonService.filterForName(name);
        return new ResponseEntity<Object>(ListPerson,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdPerson(@PathVariable int id) {
        var person = PersonService.findByIdPerson(id);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }

    @PostMapping("/")
        public String postMethodName(@RequestBody PersonDtos person){
            PersonService.save(person);
            return "Registro Ok";
    }

    @PutMapping("/{id}")
        public String update(@RequestBody PersonDtos person){
            PersonService.update(person);
            return "Actualizacion Ok";
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable int id){
            responseDTO response = PersonService.delete(id);
            return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Object> restore(@PathVariable int id){
    responseDTO response = PersonService.restore(id);
    return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}
