package com.sena.reservation.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class reservationDtos {
    private int id_reservation;
    private int id_client;
    private int quantityPersons;
    private String reservationdate;
    private String reservationtime;
    private String reservationStatus;

    // Constructor vacío requerido para la deserialización
    public reservationDtos() {
    }

    // Constructor con todos los campos
    public reservationDtos(int id_reservation, int id_client, int quantityPersons, String reservationdate,
            String reservationtime, String reservationStatus) {
        this.id_reservation = id_reservation;
        this.id_client = id_client;
        this.quantityPersons = quantityPersons;
        this.reservationdate = reservationdate;
        this.reservationtime = reservationtime;
        this.reservationStatus = reservationStatus;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getQuantityPersons() {
        return quantityPersons;
    }

    public void setQuantityPersons(int quantityPersons) {
        this.quantityPersons = quantityPersons;
    }

    public String getReservationdate() {
        return reservationdate;
    }

    public void setReservationdate(String reservationdate) {
        this.reservationdate = reservationdate;
    }

    public String getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(String reservationtime) {
        this.reservationtime = reservationtime;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public LocalDate getReservationDateAsLocalDate() {
        return LocalDate.parse(this.reservationdate);
    }
    
    public LocalTime getReservationTimeAsLocalTime() {
        return LocalTime.parse(this.reservationtime);
    }
    
}
