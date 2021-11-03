package org.example.criteria;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import java.util.List;
import java.util.stream.Collectors;

public class AgeFromCriteria implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {

        if(searchParameters.getAgeFrom() != 0) {
            persons = persons.stream()
                    .filter(person -> person.getAge() >= searchParameters.getAgeFrom())
                    .collect(Collectors.toList());
        }
        return persons;
    }
}