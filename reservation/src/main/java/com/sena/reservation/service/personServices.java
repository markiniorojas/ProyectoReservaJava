package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.reservation.interfaces.IPerson;
import com.sena.reservation.model.person;

@Service
public class personServices {

    /**
     * @Autowired = incluye la conexion de la interface
     */
    @Autowired
    private IPerson personData;

    public List<person> findAllPerson(){
        return personData.findAll();
    }

    public Optional<person> findByIdPerson(int id){
        return personData.findById(id);
    }

    public void save(person person){
        personData.save(person);
    }

    public void update(int id, person personUpdate){
        var person = findByIdPerson(id);
        if(person.isPresent()){
            person.get().setName(personUpdate.getName());
            person.get().setLast_name(personUpdate.getLast_name());
            personData.save(person.get());
        }
    }

    public void delete(int id){
        var person = findByIdPerson(id);
        if(person.isPresent()){
            personData.delete(person.get());
        }
    }

}
