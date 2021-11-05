package org.example.criteria.operations;

import org.example.criteria.Criteria;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record AndCriteria(List<Criteria> criteria) implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return  criteria.isEmpty() ? persons : criteria.stream()
                .map(criteria -> criteria.meetCriteria(persons))
                .reduce(new ArrayList<>(persons),
                        (p1, p2) -> p1.stream().filter(p2::contains).collect(Collectors.toList())
                );
    }
}
