package repository;


import controller.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable,Integer> {
    Timetable findById(int id);
}
