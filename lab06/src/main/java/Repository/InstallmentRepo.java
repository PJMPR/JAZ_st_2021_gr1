package Repository;

import Loan.Installment;
import org.springframework.data.repository.CrudRepository;

public interface InstallmentRepo extends CrudRepository<Installment,Long> {
    public Installment findById(long id);
}
