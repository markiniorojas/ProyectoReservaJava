package com.sena.reservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "bill")
public class bill {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_bill", length = 10)
    private int id_bill;

    @OneToOne
    @JoinColumn(name = "id_reservation", referencedColumnName = "id_reservation", nullable = false)
    private reservation reservation;
    
    @Column(name = "promotion_id")
    private int promotionId;

    @Column(name = "total_price")
    private double total_price;

    @Enumerated(EnumType.STRING) // Guarda el valor como texto en la BD
    @Column(name = "billStatus")
    private billStatus billStatus;

    public bill() {
    }

    public bill(int id_bill, reservation reservation, int promotionId, double total_price,
            com.sena.reservation.model.billStatus billStatus) {
        this.id_bill = id_bill;
        this.reservation = reservation;
        this.promotionId = promotionId;
        this.total_price = total_price;
        this.billStatus = billStatus;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public reservation getReservation() {
        return reservation;
    }

    public void setReservation(reservation reservation) {
        this.reservation = reservation;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public billStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(billStatus billStatus) {
        this.billStatus = billStatus;
    }

    

}
