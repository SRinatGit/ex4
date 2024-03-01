package ru.example;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @Column(name = "name")
    String name;


    public void setName(String name) {
        System.out.println("Setters ");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}