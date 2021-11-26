package Service;

import Loan.Installment;
import Repository.InstallmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InstallmentService {
    InstallmentRepo installmentRepo;

    @Autowired
    public InstallmentService(InstallmentRepo installmentRepo){
        this.installmentRepo = installmentRepo;
    }

    public void addInstallment(Installment installment){
        installmentRepo.save(installment);
    }
}
