package ru.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "ru.example")
public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class);

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


        /*
        UserRepo repo = context.getBean(UserRepo.class);
        repo.findAll().forEach(System.out::println);
        System.out.println("-- Operation SELECT done successfully");

         */
    }
}