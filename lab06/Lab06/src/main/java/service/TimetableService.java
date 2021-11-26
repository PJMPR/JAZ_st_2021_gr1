package service;

import controller.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.TimetableRepository;


@Component
public class TimetableService {

    TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public int insertData(Timetable timetable) {
        timetableRepository.save(timetable);
        return timetable.getId();
    }

    public Timetable getTimetable(int id) {
        return timetableRepository.findById(id);
    }
}
