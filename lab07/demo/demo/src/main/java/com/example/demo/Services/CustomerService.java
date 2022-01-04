package com.example.demo.Services;

import com.example.demo.Charts.BarChartGenerator;
import com.example.demo.Charts.ChartGenerator;
import com.example.demo.Charts.LinearChartGenerator;
import com.example.demo.Charts.PieCharGenerator;
import com.example.demo.Model.CustomerDto;
import com.example.demo.Model.RentalDto;
import com.example.demo.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.demo.Services.ChartsType.*;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerRepository customerRepository;

    private List<CustomerDto> getStats() {
        return customerRepository.findAll().stream()
                .map(customer -> CustomerDto.builder()
                        .customerID(customer.getCustomerId())
                        .name(customer.getFirstName())
                        .surname(customer.getLastName())
                        .moneySpent(customer.moneySpent())
                        .watchedMovies(customer.moviesWatched())
                        .build()
                ).collect(Collectors.toList());
    }

    public List<CustomerDto> rankingByMoney() {
        return getStats().stream()
                .sorted(Comparator.comparing(CustomerDto::getMoneySpent).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<CustomerDto> rankingByMovies() {
        return getStats().stream()
                .sorted(Comparator.comparing(CustomerDto::getWatchedMovies).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }


    public List<RentalDto> rentRanking(int year, int id) {
        ArrayList<RentalDto> rentInMonth = new ArrayList<>();
        ArrayList<Integer> allRents = new ArrayList<>();

        if (id == 0) {
            IntStream.rangeClosed(1, 12)
                    .forEach(i -> allRents
                            .add(customerRepository.findAll()
                                    .stream()
                                    .map(customer -> customer.moviesInMonth(year, i))
                                    .reduce(0, Integer::sum)));
        } else {
            IntStream.rangeClosed(1, 12)
                    .forEach(i -> allRents
                            .add(customerRepository.findById(id)
                                    .stream()
                                    .map(customer -> customer.moviesInMonth(year, i))
                                    .reduce(0, Integer::sum)));
        }

        IntStream.rangeClosed(1, 12)
                .forEach(i -> rentInMonth
                        .add(new RentalDto(i, allRents.get(i - 1))));

        return rentInMonth;
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

    public byte[] customerChart(ChartsType type, FieldType fieldType, String title, String xAxis, String yAxis, List<CustomerDto> data) throws IOException {
        ChartGenerator generator;
        DefaultCategoryDataset dataset;
        switch (type) {
            case BAR:
                generator = new BarChartGenerator();
                dataset = (DefaultCategoryDataset) generator.getDataSet();
                if (fieldType == FieldType.MOVIES) {
                    data.forEach(stats -> dataset.setValue(
                            (Number) stats.getWatchedMovies(),
                            "",
                            stats.getCustomerID()
                    ));
                } else {
                    data.forEach(stats -> dataset.setValue(
                            (Number) stats.getMoneySpent(),
                            "",
                            stats.getCustomerID()
                    ));
                }
                return generator.generate(title, BAR, xAxis, yAxis);

            case PIE:

                if (fieldType == FieldType.MOVIES) {
                    generator = new PieCharGenerator();
                    DefaultPieDataset pieDataset = (DefaultPieDataset) generator.getDataSet();
                    data.forEach(stats -> pieDataset.setValue(
                            stats.getCustomerID(),
                            (Number) stats.getWatchedMovies()
                    ));
                } else {
                    generator = new PieCharGenerator();
                    DefaultPieDataset pieDataset = (DefaultPieDataset) generator.getDataSet();
                    data.forEach(stats -> pieDataset.setValue(
                            stats.getCustomerID(),
                            (Number) stats.getMoneySpent()
                    ));
                }

                return generator.generate(title, PIE, "", "");
            default:
                return null;
        }
    }

//    public byte[] rentalChart(ChartsType type, String title, String xAxis, String yAxis, List<RentalDto> data) throws IOException {
//        ChartGenerator generator;
//        DefaultCategoryDataset dataset;
//        switch (type) {
//            case BAR:
//                generator = new BarChartGenerator();
//                dataset = (DefaultCategoryDataset) generator.getDataSet();
//                data.forEach(stats -> dataset.setValue(
//                        (Number) stats.getRentsInMonth(),
//                        "",
//                        stats.getMonth()
//                ));
//                return generator.generate(title, BAR, xAxis, yAxis);
//
//            case PIE:
//
//                generator = new PieCharGenerator();
//                DefaultPieDataset pieDataset = (DefaultPieDataset) generator.getDataSet();
//                data.forEach(stats -> pieDataset.setValue(
//                        stats.getCustomerID(),
//                        (Number) stats.getWatchedMovies()
//                ));
//                return generator.generate(title, PIE, "", "");
//            default:
//                return null;
//        }
//    }
}
