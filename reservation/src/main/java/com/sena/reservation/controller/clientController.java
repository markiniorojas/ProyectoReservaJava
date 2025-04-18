package com.sena.reservation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.reservation.model.client;
import com.sena.reservation.service.clientServices;

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
@RequestMapping("api/v1/client")
public class clientController {  

    @Autowired
    private clientServices clientService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllClient(){
        var ListClient = clientService.findAllClient();
        return new ResponseEntity<Object>(ListClient,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdClient(@PathVariable int id) {
        var client = clientService.findByIdClient(id);
        return new ResponseEntity<>(client,HttpStatus.OK);          
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody client client){
        boolean saved = clientService.save(client);
        if (saved) {
            return new ResponseEntity<>("Registro Ok", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error: La persona con ID " + client.getPerson().getId_person() + " no existe", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody client client){
        clientService.update(id,client);
        return "Actualizacion Ok";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        clientService.delete(id);
        return "Eliminacion Ok";
    }
}