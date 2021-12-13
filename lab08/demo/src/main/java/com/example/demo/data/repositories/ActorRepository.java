package com.example.demo.data.repositories;

import com.example.demo.data.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor findByFirstNameAndLastName(String firstName, String LastName);
}