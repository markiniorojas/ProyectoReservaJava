package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.reservation.interfaces.IClient;
import com.sena.reservation.interfaces.IPerson; // Añadir esta importación
import com.sena.reservation.model.client;

@Service
public class clientServices {
    /**
     * @Autowired = incluye la conexion de la interface
     */
     @Autowired
     private IClient clientData;
     
     @Autowired
     private IPerson personData; // Agregar esto para acceder a los datos de persona

     public List<client> findAllClient(){
         return clientData.findAll();
     }

     public Optional<client> findByIdClient(int id){
         return clientData.findById(id);
     }

     // Método para verificar si una persona existe por ID
     public boolean existsPersonById(int personId) {
         return personData.existsById(personId);
     }

     // Método modificado para guardar, verificando primero si existe la persona
     public boolean save(client client){
         // Verificar si la persona existe
         int personId = client.getPerson().getId_person(); // Asumiendo que hay un getter para id_person
         if (existsPersonById(personId)) {
             clientData.save(client);
             return true;
         }
         return false; // La persona no existe
     }

     public void update(int id, client clientUpdate){
         var client = findByIdClient(id);
         if(client.isPresent()){
             client.get().setUserName(clientUpdate.getUserName());
             client.get().setEmail(clientUpdate.getEmail());
             client.get().setPassword(clientUpdate.getPassword());
             clientData.save(client.get());
         }
     }

     public void delete(int id){
         var client = findByIdClient(id);
         if(client.isPresent()){
             clientData.delete(client.get());
         }
     }
}