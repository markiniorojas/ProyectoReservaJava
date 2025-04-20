package com.sena.reservation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.reservation.DTO.ClientDtos;
import com.sena.reservation.DTO.responseDTO;
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

    /*
     * Get: obtener datos o consultar
     * Post: registrar y crear datos
     * Put: Actualizar datos de todo el objeto
     * Patch: Actualizacion parcial
     * Delete: Eliminar objetos
     */

    @Autowired
    private clientServices ClientService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllClient(){
        var ListClient = ClientService.findAllClient();
        return new ResponseEntity<Object>(ListClient,HttpStatus.OK);
    }

    @GetMapping("/filter/{userName}")
    public ResponseEntity<Object> filterForUserName(@PathVariable String userName) {
        var ListClient = ClientService.filterForUserName(userName);
        return new ResponseEntity<Object>(ListClient,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdClient(@PathVariable int id) {
        var client = ClientService.findByIdClient(id);
        return new ResponseEntity<>(client,HttpStatus.OK);          
    }

    

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody ClientDtos client){
        boolean saved = ClientService.save(client);
        if (saved) {
            return new ResponseEntity<>("Registro Ok", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error: La persona con ID " + client.getId_person() + " no existe", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public String update(@RequestBody ClientDtos client){
        ClientService.update(client);
        return "Actualizacion Ok";
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        responseDTO response = ClientService.delete(id);
        return new ResponseEntity<Object>(response.getMessage(), response.getStatus());
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Object> restore(@PathVariable int id){
    responseDTO response = ClientService.restore(id);
    return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}