package org.example.queries.functions.prepareData;

import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public record PreparePersonData(List<Person> people) {
    public List<Double> getDouble(String fieldName) {
        return people.stream()
                .map(p -> new FieldProperties(fieldName, p))
                .filter(FieldProperties::isNotNull)
                .filter(FieldProperties::isNumber)
                .map(f -> f.getAsNumber().doubleValue())
                .collect(Collectors.toList());
    }
}
