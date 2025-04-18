package com.sena.reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "reservation")
public class reservation {

    //Id = pk
    @Id
    //que el valor sea autogenerado e autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Column indica que el valor es una columna de la tabla
    @Column(name = "id_reservation", length = 10)
    private int id_reservation;

    //Llave foranea de la tabla persona
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private client client;

    @Column(name = "resevationdate")
    private LocalDate resevationdate;

    @Column(name = "reservationtime")
    private LocalTime reservationtime;

    @Column(name = "quantityPersons")
    private int quantityPersons;

    @Enumerated(EnumType.STRING) // Guarda el valor como texto en la BD
    @Column(name = "reservationStatus")
    private reservationStatus reservationStatus;

    public reservation() {
    }

    public reservation(int id_reservation, com.sena.reservation.model.client client, LocalDate resevationdate,
            LocalTime reservationtime, int quantityPersons,
            com.sena.reservation.model.reservationStatus reservationStatus) {
        this.id_reservation = id_reservation;
        this.client = client;
        this.resevationdate = resevationdate;
        this.reservationtime = reservationtime;
        this.quantityPersons = quantityPersons;
        this.reservationStatus = reservationStatus;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }

    public LocalDate getResevationdate() {
        return resevationdate;
    }

    public void setResevationdate(LocalDate resevationdate) {
        this.resevationdate = resevationdate;
    }

    public LocalTime getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(LocalTime reservationtime) {
        this.reservationtime = reservationtime;
    }

    public int getQuantityPersons() {
        return quantityPersons;
    }

    public void setQuantityPersons(int quantityPersons) {
        this.quantityPersons = quantityPersons;
    }

    public reservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(reservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    

}
