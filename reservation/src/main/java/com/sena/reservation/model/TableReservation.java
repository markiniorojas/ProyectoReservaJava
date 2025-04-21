package com.sena.reservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "table_reservation")
public class TableReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_reservation")
    private reservation reservation;

    @ManyToOne
    @JoinColumn(name = "id_mesa")   
    private mesa mesa;

    // Constructor vacío
    public TableReservation() {
    }

    // Constructor con parámetros
    public TableReservation(int id, reservation reservation, mesa mesa) {
        this.id = id;
        this.reservation = reservation;
        this.mesa = mesa;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public reservation getReservation() {
        return reservation;
    }

    public void setReservation(reservation reservation) {
        this.reservation = reservation;
    }

    public mesa getMesa() {
        return mesa;
    }

    public void setMesa(mesa mesa) {
        this.mesa = mesa;
    }
}