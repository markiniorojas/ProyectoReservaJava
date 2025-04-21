package com.sena.reservation.DTO;

public class billDtos {
    private int id_bill;
    private int id_reservation;
    private int id_promotion;
    private double total_price;
    private String billStatus;

    public billDtos() {
    }

    public billDtos(int id_bill, int id_reservation, int id_promotion, int total_price, String billStatus) {
        this.id_bill = id_bill;
        this.id_reservation = id_reservation;
        this.id_promotion = id_promotion;
        this.total_price = total_price;
        this.billStatus = billStatus;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }
}
