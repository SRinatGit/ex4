package ru.example;

import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    //@ManyToOne
    //@JoinColumn(name = "userid")
    @Column(name = "user_id")
    //Users user_id;
    Integer user_id;

    @Column(name = "application")
    String application;


    public void setAccess_date(Date access_date) {
        //Промежуточная компонента проверки даты проверяет её наличие. Если дата не задана, то человек не вносится в базу, а сведения о имени файла и значении человека заносятся в отдельный лог.
        /*
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(access_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        this.access_date = access_date;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setApplication(String application) {
        /*//“web”, “mobile”. Если там записано что-либо иное, то оно преобразуется к виду “other:”+значение.
        if (!application.equals("web") && !application.equals("mobile")){
            application = "other" + application;
        }*/
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