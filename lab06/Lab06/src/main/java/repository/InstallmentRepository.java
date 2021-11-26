package repository;

import controller.Installment;
import org.springframework.data.repository.CrudRepository;

public interface InstallmentRepository extends CrudRepository<Installment, Integer> {
    Installment findById(int id);
}
