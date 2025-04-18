package com.sena.reservation.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.reservation.model.person;

@Repository
public interface IPerson 
    extends JpaRepository<person, Integer>{
   
} 
