import Loan.Installment;
import Loan.TimeTable;
import Loan.InstallmentCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppTest {

    InstallmentCalculator installmentCalculator = new InstallmentCalculator();

    @Test
    public void testIfCalculateInstalmentsReturnsEmptyListForTimetableWithInstallmentCountEqual0() {
        TimeTable timetable = new TimeTable(1, 0.1, 100, "constant",0 ,100 );
        List<Installment> result = installmentCalculator.calculateInstallmnet(timetable);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testIfCalculateInstalmentsReturnsTimetableWith3elements() {
        TimeTable timetable = new TimeTable(1, 0.2, 34, "DECREASING",3 ,100 );
        List<Installment> result = installmentCalculator.calculateInstallmnet(timetable);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(54, result.get(0).getCapital());
        Assertions.assertEquals(48, result.get(1).getCapital());
        Assertions.assertEquals(41, result.get(2).getCapital());
    }

    @Test
    public void testIfCalculateInstalmentsWithConstantTypeReturnsCorrectTimetable() {
        TimeTable timetable = new TimeTable(1,0.2,30.0,"CONSTANT",5, 150  );
        List<Installment> result = installmentCalculator.calculateInstallmnet(timetable);
        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals(36, result.get(0).getCapital());
        Assertions.assertEquals(36, result.get(1).getCapital());
        Assertions.assertEquals(36, result.get(4).getCapital());
    }
}
