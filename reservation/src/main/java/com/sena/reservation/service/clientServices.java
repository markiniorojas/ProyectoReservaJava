package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.reservation.DTO.ClientDtos;
import com.sena.reservation.DTO.PersonDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.interfaces.IClient;
import com.sena.reservation.interfaces.IPerson; 
import com.sena.reservation.model.client;
import com.sena.reservation.model.person;

@Service
public class clientServices {
    /**
     * @Autowired = incluye la conexion de la interface
     */
     @Autowired
     private IClient clientData;
     
     @Autowired
     private IPerson personData; // 

     public List<client> findAllClient(){
         return clientData.findAll();
     }

     public List<client> filterForUserName(String filter){
        return clientData.filterForUserName(filter);
    }

     public Optional<client> findByIdClient(int id){
         return clientData.findById(id);
     }

     // Método para verificar si una persona existe por ID
     public boolean existsPersonById(int personId) {
         return personData.existsById(personId);
     }

     // Método modificado para guardar, verificando primero si existe la persona
     public boolean save(ClientDtos client){
         // Verificar si la persona existe
         int personId = ClientDtos.getId_person(); 
         if (existsPersonById(personId)) {
            Optional<person> personEntity = personData.findById(personId);
            if (personEntity.isPresent()) {
                // Crear una nueva entidad cliente
                client newClient = new client(
                    0,
                    personEntity.get(),
                    ClientDtos.getUserName(),
                    ClientDtos.getEmail(),
                    ClientDtos.getPassword()
                );
                clientData.save(newClient);
                return true;
            }
         }
         return false; 
     }

     public client converRegisterToClient(ClientDtos clientDto){ 
        Optional<person> personEntity = personData.findById(clientDto.getId_person());
        
        // Si la persona existe, crear y devolver el cliente
        if(personEntity.isPresent()) {
            return new client(
                0,
                personEntity.get(),
                clientDto.getUserName(),
                clientDto.getEmail(),
                clientDto.getPassword()
            );
        }
        
        // Si la persona no existe, retornar null
        return null;
    }
     public void update(ClientDtos clientUpdate){
         var client = findByIdClient(clientUpdate.getId_client());
         if(client.isPresent()){
             client.get().setUserName(clientUpdate.getUserName());
             client.get().setEmail(clientUpdate.getEmail());
             client.get().setPassword(clientUpdate.getPassword());
             clientData.save(client.get());
         }
     }

     public responseDTO delete(int id){
         var client = findByIdClient(id);
         responseDTO response = new responseDTO();  
         if(client.isPresent()){
            client.get().setActive(false);
            clientData.save(client.get());
            
            response.setStatus(HttpStatus.OK);
            response.setMessage("Cliente eliminado con éxito");
            return response;
         }else{
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Cliente no encontrado");
            return response;
         }
     }

     public responseDTO restore(int id) {
         // Buscamos el cliente por ID (activa o inactiva)
         var client = clientData.findById(id);
         responseDTO response = new responseDTO();
         
         if (client.isPresent()) {
             // Si el cliente existe pero está inactivo
             if (!client.get().isActive()) {
                 // La reactivamos
                 client.get().setActive(true);
                 clientData.save(client.get());
                 
                 response.setStatus(HttpStatus.OK);
                 response.setMessage("Cliente restaurado con éxito");
                 return response;
             } else {
                 response.setStatus(HttpStatus.BAD_REQUEST);
                 response.setMessage("El cliente ya está activo");  
             }
         } else {
             response.setStatus(HttpStatus.NOT_FOUND);
             response.setMessage("Cliente no encontrado");
             return response;
         }
     }
}