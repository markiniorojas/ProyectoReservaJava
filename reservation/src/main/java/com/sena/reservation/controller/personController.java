package com.sena.reservation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.reservation.model.person;
import com.sena.reservation.service.personServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
    private personServices personService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllPerson() {
        var ListPerson = personService.findAllPerson();
        return new ResponseEntity<Object>(ListPerson,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdPerson(@PathVariable int id) {
        var person = personService.findByIdPerson(id);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }

    @PostMapping("/")
        public String save(@RequestBody person person){
            personService.save(person);
            return "Registro Ok";
    }

    @PutMapping("/{id}")
        public String update(@PathVariable int id, @RequestBody person person){
            personService.update(id, person);
            return "Actualizacion Ok";
    }

    @DeleteMapping("/{id}")
        public String delete(@PathVariable int id){
            personService.delete(id);
            return "Eliminacion Ok";
    }


}
