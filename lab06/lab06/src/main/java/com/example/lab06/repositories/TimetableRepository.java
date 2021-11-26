package com.example.lab06.repositories;

import com.example.lab06.func.Timetable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimetableRepository extends CrudRepository<Timetable, Long> {
    @Query("select t from Timetable t where t.calculation.id = ?1")
    List<Timetable> findAllById(long id);

}
