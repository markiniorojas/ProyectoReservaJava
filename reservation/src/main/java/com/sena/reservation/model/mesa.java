package com.sena.reservation.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class mesa {
    //ID = PK
    @Id
    //Valor incremental Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_mesa", length = 10)
    private int id_mesa;

    @Column(name = "capacity_table", length = 10)
    private int capacity_table;

    @Enumerated(EnumType.STRING) // Guarda el valor como texto en la base de datos
    @Column(name = "tableStatus")
    private mesaStatus tableStatus;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<TableReservation> tableReservations;

    public mesa() {
    }

    public mesa(int id_mesa,int capacity_table,mesaStatus tableStatus) {
        this.id_mesa = id_mesa;
        this.capacity_table = capacity_table;
        this.tableStatus = tableStatus;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }


    public int getCapacity_table() {
        return capacity_table;
    }

    public void setCapacity_table(int capacity_table) {
        this.capacity_table = capacity_table;
    }

    public mesaStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(mesaStatus tableStatus) {
        this.tableStatus = tableStatus;
    }

    public List<TableReservation> getTableReservations() {
        return tableReservations;
    }

    public void setTableReservations(List<TableReservation> tableReservations) {
        this.tableReservations = tableReservations;
    }
}
