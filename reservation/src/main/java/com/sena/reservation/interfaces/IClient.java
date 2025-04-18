package com.sena.reservation.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.reservation.model.client;

@Repository
public interface IClient 
    extends JpaRepository<client, Integer> {

}