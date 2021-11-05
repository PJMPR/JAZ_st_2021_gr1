package org.example.criteria.filters;

import org.example.criteria.Criteria;
import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class IncomeToCriteria implements Criteria {
    double number;

    public IncomeToCriteria(double number){this.number = number;}

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return (number <= 0) ? persons : persons.stream().filter(person -> person.getIncome() <= number).collect(Collectors.toList());
    }
}
