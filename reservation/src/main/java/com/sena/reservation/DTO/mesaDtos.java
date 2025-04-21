package com.sena.reservation.DTO;

public class mesaDtos {
    private int id_mesa;
    private int capacityTable;
    private String tableStatus;

    public mesaDtos() {
    }

    public mesaDtos(int id_mesa, int capacityTable, String tableStatus) {
        this.id_mesa = id_mesa;
        this.capacityTable = capacityTable;
        this.tableStatus = tableStatus;
    }

    public int getId_Mesa() {
        return id_mesa;
    }

    public void setId_Mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getCapacityTable() {
        return capacityTable;
    }

    public void setCapacityTable(int capacityTable) {
        this.capacityTable = capacityTable;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    

}
