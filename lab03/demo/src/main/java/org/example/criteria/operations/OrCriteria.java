package org.example.criteria.operations;

import org.example.criteria.Criteria;
import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class OrCriteria implements Criteria {

    private final List<Criteria> criteria;

    public OrCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        if(!criteria.isEmpty())
            return criteria.stream().flatMap(c -> c.meetCriteria(persons).stream()).distinct().collect(Collectors.toList());
        return persons;
    }
}
