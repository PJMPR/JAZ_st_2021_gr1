package com.example.demo.Services;

import com.example.demo.Charts.BarChartGenerator;
import com.example.demo.Charts.ChartGenerator;
import com.example.demo.Charts.LinearChartGenerator;
import com.example.demo.Charts.PieCharGenerator;
import com.example.demo.Model.RentalDto;
import com.example.demo.data.Payment;
import com.example.demo.data.Rental;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RentalRepository;

import lombok.RequiredArgsConstructor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.demo.Services.ChartsType.*;


@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;

    public int getIncomeInMonth(int year, int month) {
        Timestamp timeFrom = Timestamp.valueOf(year + "-" + month + "-01 00:00:01");
        Timestamp timeTo = Timestamp.valueOf(year + "-" + month + "-31 23:59:59");
        return rentalRepository.findAll().stream()
                .map(Rental::getPaymentsByRentalId)
                .map(x -> x.stream().filter(p -> p.getPaymentDate().after(timeFrom) && p.getPaymentDate().before(timeTo)))
                .map(x -> x.map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add).intValue())
                .reduce(0, Integer::sum);
    }

    public List<RentalDto> getIncomeByMonth(int year) {
        ArrayList<RentalDto> rentalDto = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(getIncomeInMonth(year, i)));
        IntStream.rangeClosed(1, 12).forEach(i -> rentalDto.add(new RentalDto(i, (temp.get(i - 1)))));
        return rentalDto;
    }

    public byte[] rentalChart(ChartsType type, String title, String xAxis, String yAxis, List<RentalDto> data) throws IOException{

        ChartGenerator generator;
        DefaultCategoryDataset dataset;
        switch (type){
            case BAR:
                generator = new BarChartGenerator();
                dataset = (DefaultCategoryDataset) generator.getDataSet();
                data.forEach(stats -> dataset.setValue(
                        (Number)stats.getRentsInMonth(),
                        "",
                        stats.getMonth()
                ));
                return generator.generate(title, BAR, xAxis, yAxis);
            case LINEAR:
                generator = new LinearChartGenerator();
                dataset = (DefaultCategoryDataset) generator.getDataSet();
                data.forEach(stats -> dataset.setValue(
                        (Number)stats.getRentsInMonth(),
                        "",
                        stats.getMonth()
                ));
                return generator.generate(title, LINEAR, xAxis, yAxis);
            case PIE:
                generator = new PieCharGenerator();
                DefaultPieDataset pieDataset = (DefaultPieDataset) generator.getDataSet();
                data.forEach(stats -> pieDataset.setValue(
                        stats.getMonth(),
                        (Number)stats.getRentsInMonth()
                ));
                return generator.generate(title, PIE, "", "");
            default:
                return null;
        }
    }

}