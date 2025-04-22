package com.sena.reservation.DTO;

public class tablaReservationDtos {
    private int id_tableReservation;
    private int id_reservation;
    private int id_mesa;

    public tablaReservationDtos() {
    }

    public tablaReservationDtos(int id_tableReservation, int id_reservation, int id_mesa, String tableStatus) {
        this.id_tableReservation = id_tableReservation;
        this.id_reservation = id_reservation;
        this.id_mesa = id_mesa;
    }

    public int getId_tableReservation() {
        return id_tableReservation;
    }

    public void setId_tableReservation(int id_tableReservation) {
        this.id_tableReservation = id_tableReservation;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

}
