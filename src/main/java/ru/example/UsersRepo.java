package ru.example;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, Long>{
}
