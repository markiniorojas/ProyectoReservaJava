package com.sena.reservation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.reservation.model.client;
import com.sena.reservation.service.clientServices;

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
        public String save(@RequestBody client client){
            clientService.save(client);
            return "Registro Ok";
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
