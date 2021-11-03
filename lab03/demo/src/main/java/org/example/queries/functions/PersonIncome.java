package org.example.queries.functions;

import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonIncome extends CalculateField {
    public PersonIncome(List<Person> items) {
        super("age", items.stream().collect(Collectors.summarizingDouble(Person::getIncome)));
    }
}
