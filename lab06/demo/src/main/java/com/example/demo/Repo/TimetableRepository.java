package com.example.demo.Repo;

import com.example.demo.PKO.Timetable;
import org.springframework.data.repository.CrudRepository;

public interface TimetableRepository extends CrudRepository<Timetable, Integer> {
    Timetable findById (int id);
}
