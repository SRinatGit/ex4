package ru.example;


import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication(scanBasePackages = "ru.example")
public class Main {
    public static void main(String[] args) throws Exception {
        /*логика следующая
        1. открываем ApplicationContext context = SpringApplication.run(Main.class);
        2. читаем данные из файла построчно
        3. для каждой строки, ищем в БД запись с пользователем по полю username
            - не нашли, создаем запись в таблице пользователей (при создании поле FIO нормализуем, что бы первые буквы были заглавными)
                + создаем запись в таблице авторизаций
            - нашли, добавляем новую запись в таблицу авторизаций
        4. в конце цикла все закрываем
        */
        //-------------------------- чтение данных из файла и запись в лист----------------
        // открываем файл
        Scanner in = new Scanner(System.in);
        System.out.print("Input file path: ");
        String filePath = in.nextLine(); // путь должен быть в формате C:\temp\autoriz.csv
        System.out.printf("Your file: ", filePath);
        in.close();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // считываем построчно
        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<Autorization> autorizationList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            Autorization autorization = new Autorization();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    autorization.setUsername(data);
                else if (index == 1)
                    autorization.setFio(data);
                /*else if (index == 2)
                    /*String dateInString = "19590709";
                    LocalDate date = LocalDate.parse(dateInString, DateTimeFormatter.BASIC_ISO_DATE);
                    * /

                    //LocalDate date = LocalDate.parse("2018-05-05");
                    //LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
                    //autorization.setAccess_date(new LocalDate.parse(data, formatter));
                    //autorization.setAccess_date(LocalDate.parse(data, DateTimeFormatter.ofPattern("DD/MMMM/YYYY")));
                    //autorization.setAccess_date(LocalDate.parse("01/01/2024"));
                    LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
                    */
                else if (index == 3)
                    autorization.setApplication(data);
                else
                    System.out.println("Некорректные данные::" + data);
                index++;
            }
            index = 0;
            System.out.println(autorization.toString());
            autorizationList.add(autorization);
        }

        //закрываем наш ридер
        reader.close();

        System.out.println(autorizationList);

        //-------------------------- запись данных в базу --------------

 //       ApplicationContext context = SpringApplication.run(Main.class);
/*
        System.out.println("--1");
        DepartmentRepo repo = context.getBean(DepartmentRepo.class);
        System.out.println("--2");
        //Department dep = repo.findById(d, 1L);
        repo.findAll().forEach(System.out::println);
        System.out.println("--2.1 " +  repo.findById(1L));
        Department dep = repo.findById(1L).get(); //.setName("ssssss");
        dep.setName("ddddddddd");
        System.out.println("--3");
        repo.save(dep);

        System.out.println("--");
        Department dep2 = new Department();
        //dep2.setId(6l);
        dep2.setName("tttttttttttt");
        repo.save(dep2);

        System.out.println("--5");
        repo.findAll().forEach(System.out::println);
        System.out.println("--6");
*/

//        System.out.println("--1");
//        UsersRepo repoUser = context.getBean(UsersRepo.class);
//        LoginsRepo repoLogins = context.getBean(LoginsRepo.class);
//        System.out.println("--2");
//        repoUser.findAll().forEach(System.out::println); // смотрим все записи в БД
/*
        System.out.println("Обновим пользователя с ID = 1 " +  repo.findById(1L));
        Users user = repo.findById(1L).get(); // получим пользователя по ID
        user.setUsername("firstUser");
        user.setFio("Иванов петр сергеевич");
        System.out.println("--3");
        repo.save(user); // сохраняем в БД
*/

//        System.out.println("Добавим нового пользователя ");
//        Users user2 = new Users();
//        user2.setUsername("ForUser");
//        user2.setFio("чубиков сергей мунисович");
//
//        Logins log1 = new Logins(); // добавим данные авторизации
////        log1.setAccess_date(parseTimestamp("2020-05-01 12:30:00"));
//        log1.setApplication("Прога1");
//        log1.setUser_id(user2.getId());
//
//        Logins log2 = new Logins(); // добавим данные авторизации
////        log2.setAccess_date(parseTimestamp("2020-05-01 12:30:00"));
//        log2.setApplication("Прога1");
//        log2.setUser_id(user2.getId());
//
//        System.out.println(user2.toString());
//
//        repoUser.save(user2);
//        repoLogins.save(log1);
//        //repoLogins.save(log2);
//
//        repoUser.findAll().forEach(System.out::println);
//        System.out.println("Закончили");
    }
}