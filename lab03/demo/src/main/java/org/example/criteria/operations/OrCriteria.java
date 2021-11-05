package org.example.criteria.operations;

import org.example.criteria.Criteria;
import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public record OrCriteria(List<Criteria> criteria) implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return  criteria.isEmpty() ? persons : criteria.stream()
                .flatMap(c -> c.meetCriteria(persons).stream()).distinct()
                .collect(Collectors.toList());
    }
}
