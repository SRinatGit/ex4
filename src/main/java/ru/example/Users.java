package ru.example;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //long id; если в базе тип Integer
    Integer id; // если в базе тип bigint
    @Column(name = "username")
    String username;
    @Column(name = "fio")
    String fio;

    public Users() {

    }
    public Users(String username, String fio) {
        this.username = username;
        this.fio = fio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFio(String fio) {
      /*  String fio2 = "";
        fio2 = fio2 + fio.substring(0, 1).toUpperCase(); //первый символ делаем заглавным
        for (int i = 1; i < fio.length(); i++) {
            // смотрим, был ли слева пробел:
            if (" ".equals(fio.substring(i-1, i)))
                fio2 = fio2 + fio.substring(i, i+1).toUpperCase();
            else
                fio2 = fio2 + fio.substring(i, i+1);
        }
*/
        this.fio = fio; //fio2;
    }

    public Integer getId() {
        return id;
    }



    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}