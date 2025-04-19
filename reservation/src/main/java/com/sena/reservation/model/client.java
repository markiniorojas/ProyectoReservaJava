package com.sena.reservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "client")
public class client {
   //Id = pk
   @Id 
   //El valor autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_client", length = 10)
    private int id_client;

    //Llave foranea de la tabla persona
    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = false)
    private person person;

    @Column(name = "userName", length = 20)
    private String userName;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    public client() {
    }

    public client(int id_client, person person, String userName, String email, String password) {
        this.id_client = id_client;
        this.person = person;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.active = true;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public person getPerson() {
        return person;
    }

    public void setPerson(person person) {
        this.person = person;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
