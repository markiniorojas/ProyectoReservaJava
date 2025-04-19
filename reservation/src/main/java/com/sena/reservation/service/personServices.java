package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.reservation.DTO.PersonDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.interfaces.IPerson;
import com.sena.reservation.model.person;

@Service
public class personServices {

    /**
     * @Autowired = incluye la conexion de la interface
     */

    @Autowired
    private IPerson PersonData;

    public List<person> findAllPerson(){
        return PersonData.findAllActive();
    }

    public List<person> filterForName(String filter){
        return PersonData.filterForName(filter);
    }

    public Optional<person> findByIdPerson(int id){
        return PersonData.findById(id);
    }

    public void save(PersonDtos person){
        PersonData.save(converRegisterToPerson(person));
    }

    public person converRegisterToPerson(PersonDtos person){
        return new person(
                0,
                person.getName(),
                person.getLastName(),
                person.getPhone(),
                person.getAge()
        );
    }

    public void update(PersonDtos PersonUpdate){
        var person = findByIdPerson(PersonUpdate.getId());
        if(person.isPresent()){
            person.get().setName(PersonUpdate.getName());
            person.get().setLast_name(PersonUpdate.getLastName());
            person.get().setPhone(PersonUpdate.getPhone());
            person.get().setAge(PersonUpdate.getAge());
            PersonData.save(person.get());
        }
    }

    public responseDTO delete(int id){
        var person = findByIdPerson(id);
        responseDTO response = new responseDTO();
        if(person.isPresent()){
            // En lugar de eliminar, marcar como inactivo
            person.get().setActive(false);
            PersonData.save(person.get());
            
            response.setStatus(HttpStatus.OK);
            response.setMessage("Persona eliminada con éxito");
            return response;
        }else{
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Persona no encontrada");
            return response;
        }
    }

    public responseDTO restore(int id) {
        // Buscamos la persona por ID (activa o inactiva)
        var person = PersonData.findById(id);
        responseDTO response = new responseDTO();
        
        if (person.isPresent()) {
            // Si la persona existe pero está inactiva
            if (!person.get().isActive()) {
                // La reactivamos
                person.get().setActive(true);
                PersonData.save(person.get());
                
                response.setStatus(HttpStatus.OK);
                response.setMessage("Persona restaurada con éxito");
            } else {
                // Si ya está activa
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("La persona ya se encuentra activa");
            }
        } else {
            // Si no existe
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Persona no encontrada");
        }
        
        return response;
    }

}
