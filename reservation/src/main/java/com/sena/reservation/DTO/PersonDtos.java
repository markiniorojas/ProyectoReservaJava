package com.sena.reservation.DTO;

public class PersonDtos{
    private int id;
    private String Name;
    private String LastName;
    private int Phone;
    private int Age;
    // Constructor vacío requerido para la deserialización
    public PersonDtos() {
    }
    
    // Constructor con todos los campos
    public PersonDtos(int id, String name, String lastName, int phone, int age) {
        this.id = id;
        this.Name = name;
        this.LastName = lastName;
        this.Phone = phone;
        this.Age = age;
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String name) {
        this.Name = name;
    }
    
    public String getLastName() {
        return LastName;
    }
    
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }
    
    public int getPhone() {
        return Phone;
    }
    
    public void setPhone(int phone) {
        this.Phone = phone;
    }
    
    public int getAge() {
        return Age;
    }
    
    public void setAge(int age) {
        this.Age = age;
    }
}
