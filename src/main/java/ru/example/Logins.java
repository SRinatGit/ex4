package ru.example;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Logins")
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id; //если в базе тип Integer
    //Integer id; // если в базе тип bigint
    @Column(name = "access_date")
    Date access_date;
    @Column(name = "user_id")
    long user_id;

    @Column(name = "application")
    String application;

    public void setAccess_date(Date access_date) {
        this.access_date = access_date;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Logins{" +
                "id=" + id +
                ", access_date='" + access_date + '\'' +
                ", user_id='" + user_id + '\'' +
                ", application='" + application + '\'' +
                '}';
    }
}