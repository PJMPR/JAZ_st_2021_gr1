package org.example.criteria.filters;

import org.example.criteria.Criteria;
import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class AgeFromCriteria implements Criteria {
    int number;

    public AgeFromCriteria(int number){
        this.number = number;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return (number <= 0) ? persons : persons.stream().filter(person -> person.getAge() >= number).collect(Collectors.toList());
    }
}