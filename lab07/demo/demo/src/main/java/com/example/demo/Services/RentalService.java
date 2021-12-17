package com.example.demo.Services;

import com.example.demo.Model.RentalDto;
import com.example.demo.data.Payment;
import com.example.demo.data.Rental;
import com.example.demo.repositories.RentalRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

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
}
