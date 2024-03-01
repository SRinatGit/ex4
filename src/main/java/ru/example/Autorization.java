package ru.example;

import java.util.Date;

public class Autorization {

    private String username;
    private String fio;
    private Date access_date;
    private String application;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getAccess_date() {
        return access_date;
    }

    public void setAccess_date(Date access_date) {
        this.access_date = access_date;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Autorization{" +
                "username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                ", access_date=" + access_date +
                ", application='" + application + '\'' +
                '}';
    }
}
