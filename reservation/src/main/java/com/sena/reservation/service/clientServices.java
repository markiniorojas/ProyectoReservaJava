package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.reservation.DTO.ClientDtos;
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
     private IClient ClientData;
     
     @Autowired
     private IPerson PersonData; // 

     public List<client> findAllClient(){
         return ClientData.findAllActive();
     }

     public List<client> filterForUserName(String filter){
        return ClientData.filterForUserName(filter);
    }

     public Optional<client> findByIdClient(int id){
         return ClientData.findActiveById(id);
     }

     // Método para verificar si una persona existe por ID
     public boolean existsPersonById(int personId) {
         return PersonData.existsById(personId);
     }

     // Método modificado para guardar, verificando primero si existe la persona
     public boolean save(ClientDtos clientDto){
        // Verificar si la persona existe
        int personId = clientDto.getId_person(); 
        if (existsPersonById(personId)) {
            client newClient = converRegisterToClient(clientDto);
            if (newClient != null) {
                ClientData.save(newClient);
                return true;
            }
        }
        return false; 
    }

     public client converRegisterToClient(ClientDtos clientDto){ 
        Optional<person> personEntity = PersonData.findById(clientDto.getId_person());
        
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
    
    public void update(ClientDtos ClientUpdate){
        var existingClient = findByIdClient(ClientUpdate.getId_client());
        if(existingClient.isPresent()){
            var client = existingClient.get();
            // Volver a cargar la persona desde la BD
            Optional<person> personEntity = PersonData.findById(ClientUpdate.getId_person());
            if(personEntity.isEmpty()) {
                // Si no existe, no actualices
                throw new RuntimeException("La persona con ID " + ClientUpdate.getId_person() + " no existe.");
            }
            // Asignar persona y los demás datos
            client.setPerson(personEntity.get());
            client.setUser_name(ClientUpdate.getUserName());
            client.setEmail(ClientUpdate.getEmail());
            client.setPassword(ClientUpdate.getPassword());
            
            ClientData.save(client); 
        }
    }
    

     public responseDTO delete(int id){
         var client = findByIdClient(id);
         responseDTO response = new responseDTO();  
         if(client.isPresent()){
            client.get().setActive(false);
            ClientData.save(client.get());
            
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
         var client = ClientData.findById(id);
         responseDTO response = new responseDTO();
         
         if (client.isPresent()) {
             // Si el cliente existe pero está inactivo
             if (!client.get().isActive()) {
                 // La reactivamos
                 client.get().setActive(true);
                 ClientData.save(client.get());
                 
                 response.setStatus(HttpStatus.OK);
                 response.setMessage("Cliente restaurado con éxito");
             } else {
                 response.setStatus(HttpStatus.BAD_REQUEST);
                 response.setMessage("El cliente ya está activo");  
             }
         } else {
             response.setStatus(HttpStatus.NOT_FOUND);
             response.setMessage("Cliente no encontrado");
         }
         return response;
     }
}