package com.lab06.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.lab06.demo.models.Timetable;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Long> {
    @Query("select t from Timetable t where t.calculation.id = ?1")
    List<Timetable> findAllById(long id);
}
