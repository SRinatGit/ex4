package ru.example;

import org.springframework.data.repository.CrudRepository;

public interface LoginsRepo extends CrudRepository<Users, Long> {
}