package Repository;

import Loan.TimeTable;
import org.springframework.data.repository.CrudRepository;

public interface TimeTabelRepo extends CrudRepository<TimeTable,Long> {
    public TimeTable findById(long id);
}
