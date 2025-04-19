package com.sena.reservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * anotacion bean para la clase persona
 */
@Entity(name = "person")
public class person {
    //@Id = pk
    @Id
    //el valor sea autogenerado e autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column indica que el valor es una columna de la tabla
    @Column(name = "id_person", length = 10)
    private int id_person;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "last_name", length = 30)
    private String last_name;

    @Column(name = "phone", length = 10)
    private int phone;

    @Column(name = "age", length = 2)
    private int age;

    // Nuevo campo para soft delete
    @Column(name = "active", nullable = false)
    private boolean active = true;

    public person() {
    }

    public person(int id_person, String name, String last_name, int phone, int age) {
        this.id_person = id_person;
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
        this.age = age;
        this.active = true;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
