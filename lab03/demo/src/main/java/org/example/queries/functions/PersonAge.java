package org.example.queries.functions;

import org.example.model.Person;

import java.lang.reflect.Field;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class PersonAge extends CalculateField{
    public PersonAge(List<Person> items){
        super("income", items.stream().collect(Collectors.summarizingDouble(Person::getAge)));
    }
}
