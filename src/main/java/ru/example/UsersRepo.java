package ru.example;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<Users, Long>{
    Users findByUsername(String username);
}
