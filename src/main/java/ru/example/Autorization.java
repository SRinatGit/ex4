package ru.example;

import java.util.Date;

public class Autorization {

    private String username;
    private String fio;
    private String accessDateStr;
    private Date accessDate;
    private String application;
    private String fileName;
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

    public String getaccessDateStr() {
        return accessDateStr;
    }

    public void setaccessDateStr(String accessDateStr) {
        this.accessDateStr = accessDateStr;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Autorization{" +
                "username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                ", accessDateStr='" + accessDateStr + '\'' +
                ", accessDate=" + accessDate +
                ", application='" + application + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
