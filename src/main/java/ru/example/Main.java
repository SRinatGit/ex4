package ru.example;


import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Timestamp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@SpringBootApplication(scanBasePackages = "ru.example")
public class Main {
    private static final String CSV_FILE_PATH = "C:/temp/";
    // проверка наличия даты, если дата корректная, то запишем в виде даты
    /*public static boolean checkDate(Autorization a){
        String access_date = a.getaccessDateStr();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(access_date);
            a.setAccessDate(startDate);
        } catch (ParseException e) {
            //e.printStackTrace();
            // преобразовать не удалось, пишем в лог
            return false;
        }
        return true;
    }
    */
    public static void main(String[] args) throws Exception {

        ValidatorComponent validatorComponent = new ValidatorComponent();
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

        System.out.println("START READER autoriz2UTF.csv");
        // блок получения пути и файла

        // получим Путь
        Scanner in = new Scanner(System.in);
        System.out.print("Input file path: ");
        String filePath = in.nextLine(); // путь должен быть в формате C:\temp\autoriz.csv
        filePath = "C:\\temp\\"; // TODO на время разработки заглушки
        System.out.printf("Your path: ", filePath);
        String fileName = in.nextLine(); // путь должен быть в формате C:\temp\autoriz.csv
        fileName = "autoriz2UTF.csv"; // TODO на время разработки заглушки
        System.out.printf("Your file: ", fileName);
        in.close();


        System.out.println();
        AutorizationParser autorizationParser = new AutorizationParser();
        List<Autorization> autorizationList = autorizationParser.parse(filePath, fileName);


        System.out.println("FINISH READER autoriz2UTF.csv");

        // теперь надо каждую запись из autorizationList обрабатывать по условиям, если они все выполняться
        // то записать данные в базу по двум таблицам
        ApplicationContext context = SpringApplication.run(Main.class);
        UsersRepo repoUser = context.getBean(UsersRepo.class);

        for (ListIterator<Autorization> iter = autorizationList.listIterator(); iter.hasNext(); ) {
            Autorization element = iter.next();
            // Промежуточная компонента проверки даты проверяет её наличие. Если дата не задана, то человек не вносится в базу, а сведения о имени файла и значении человека заносятся в отдельный лог.
            //if (checkDate(element)) {
            System.out.println("Обработаем запись " + element.toString());
            if (validatorComponent.isValidDate(element)) {

                element.setFio(validatorComponent.isValidateFio(element.getFio())); // Нормализуем ФИО
                element.setApplication(validatorComponent.isApplication(element.getApplication())); // Нормализуем приложение
                // обработка таблицы user
                Users user = repoUser.findByUsername(element.getUsername());
                if (user != null) {
                    System.out.println("нашли" + user);
                    //Вопрос, если изменилось ФИО, тогда надо обновлять ?, если да, то раскоментарить
//                    user.setFio(element.getFio());
//                    repoUser.save(user);
                } else {
                    System.out.println("Пользователь не найден.");

                    user = new Users(); // если не нашли, тогда создаем
                    user.setUsername(element.getUsername());
                    user.setFio(element.getFio());
                    repoUser.save(user);
                }

                // обработка таблицы Logins
                Logins logins = new Logins();
                logins.setUser_id(user.getId());
                logins.setAccess_date(element.getAccessDate()); // дату из строки переводим в дату в setAccess_date

                logins.setApplication(element.getApplication()); // тип приложения соответствует одному из: “web”, “mobile”, others, сразу проверяется
                LoginsRepo repoLogins = context.getBean(LoginsRepo.class);
                //repoLogins.saveAll(logins);
                repoLogins.save(logins);

            }
        }

        // открываем файл
        //Scanner in = new Scanner(System.in);
        //System.out.print("Input file path: ");
        //String filePath = in.nextLine(); // путь должен быть в формате C:\temp\autoriz.csv
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\temp\\autoriz2UTF.csv"))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] parts = line.split(";");
//                    String username = parts[0].trim();
//                    String password = parts[1].trim();
//                    String fio = parts[3].trim();
//                    String appName = parts[4].trim();
//
//                    // Check if user with username already exists
//                    User existingUser = (User) session.createQuery("FROM users WHERE username = :username")
//                            .setParameter("username", username)
//                            .uniqueResult();
//                    Users user;
//                    if (existingUser == null) {
//                        user = new Users();
//                        user.setFio(fio);
//                        user.setUsername(username);
//                        session.save(user);
//                    }
//                    else {
//                        user = new Users();
//                        user.setFio(fio);
//                        user.setUsername(username);
//
//                    }
//                    session.save(new Logins(new Date(), user, appName)
//
//                    );
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            transaction.commit();
//        }
//
//        sessionFactory.close();
    }
}