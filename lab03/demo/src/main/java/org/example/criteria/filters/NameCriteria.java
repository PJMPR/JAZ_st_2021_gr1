package org.example.criteria.filters;

import org.example.criteria.Criteria;
import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class NameCriteria implements Criteria {
    String string;

    public NameCriteria(String string){this.string = string;}

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return (string == null) ? persons : persons.stream().filter(person -> person.getName().equalsIgnoreCase(string)).collect(Collectors.toList());
    }
}
