package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.reservation.interfaces.IClient;
import com.sena.reservation.model.client;

@Service
public class clientServices {
    /**
     * @Autowired = incluye la conexion de la interface
     */
     @Autowired
        private IClient clientData;

        public List<client> findAllClient(){
            return clientData.findAll();
        }

        public Optional<client> findByIdClient(int id){
            return clientData.findById(id);
        }

        public void save(client client){
            clientData.save(client);
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
