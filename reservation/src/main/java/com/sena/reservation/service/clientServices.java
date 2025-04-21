package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private IClient ClientData;

    @Autowired
    private IPerson PersonData;

    // 🔁 Retorna lista de ClientDtos (solo con id_person)
    public List<ClientDtos> findAllClient() {
        return ClientData.findAllActive()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    

    public List<ClientDtos> filterForUserName(String filter) {
        List<client> clients = ClientData.filterForUserName(filter);
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 🔁 Retorna Optional con ClientDtos
    public Optional<ClientDtos> findByIdClient(int id) {
        return ClientData.findActiveById(id)
                .map(this::convertToDto);
    }

    public boolean existsPersonById(int personId) {
        return PersonData.existsById(personId);
    }

    public boolean save(ClientDtos clientDto) {
        int personId = clientDto.getId_person();
        if (existsPersonById(personId)) {
            client newClient = convertRegisterClient(clientDto);
            if (newClient != null) {
                ClientData.save(newClient);
                return true;
            }
        }
        return false;
    }

    public client convertRegisterClient(ClientDtos clientDtos) {
        person Person = new person();
        Person.setId_person(clientDtos.getId_person());
        return new client(
                0, // Id autogenerado
                Person,
                clientDtos.getUserName(),
                clientDtos.getEmail(),
                clientDtos.getPassword()
        );
    }

    public ClientDtos convertToDto(client client) {
        ClientDtos dto = new ClientDtos();
        dto.setId_client(client.getId_client());
    
        // Validamos si hay persona asociada
        if (client.getPerson() != null) {
            dto.setId_person(client.getPerson().getId_person());
        } else {
            dto.setId_person(0); // o déjalo sin setear si prefieres no mostrarlo
        }
    
        dto.setUserName(client.getUser_name());
        dto.setEmail(client.getEmail());
        dto.setPassword(client.getPassword());
    
        return dto;
    }
    

    public void update(ClientDtos ClientUpdate) {
        var existingClient = ClientData.findActiveById(ClientUpdate.getId_client());
        if (existingClient.isPresent()) {
            var client = existingClient.get();
            Optional<person> personEntity = PersonData.findById(ClientUpdate.getId_person());
            if (personEntity.isEmpty()) {
                throw new RuntimeException("La persona con ID " + ClientUpdate.getId_person() + " no existe.");
            }
            client.setPerson(personEntity.get());
            client.setUser_name(ClientUpdate.getUserName());
            client.setEmail(ClientUpdate.getEmail());
            client.setPassword(ClientUpdate.getPassword());
            ClientData.save(client);
        }
    }

    public responseDTO delete(int id) {
        var client = ClientData.findActiveById(id);
        responseDTO response = new responseDTO();
        if (client.isPresent()) {
            client.get().setActive(false);
            ClientData.save(client.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Cliente eliminado con éxito");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Cliente no encontrado");
        }
        return response;
    }

    public responseDTO restore(int id) {
        var client = ClientData.findById(id);
        responseDTO response = new responseDTO();

        if (client.isPresent()) {
            if (!client.get().isActive()) {
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
