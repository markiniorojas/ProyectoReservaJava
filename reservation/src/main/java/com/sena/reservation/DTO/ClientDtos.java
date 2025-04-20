package com.sena.reservation.DTO;

public class ClientDtos {
    private int id_client;
    private int id_person;
    private String userName;
    private String email;
    private String password;

    // Constructor vacío requerido para la deserialización
    public ClientDtos() {
    }

    // Constructor con todos los campos
    public ClientDtos(int id_client, int id_person, String userName, String email, String password) {
        this.id_client = id_client;
        this.id_person = id_person;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    // Getters y setters
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
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
}
