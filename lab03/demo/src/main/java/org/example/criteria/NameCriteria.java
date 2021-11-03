package org.example.criteria;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class NameCriteria implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {

        if(searchParameters.getName() != null) {
            return persons.stream()
                    .filter(person -> searchParameters.getName().toLowerCase(Locale.ROOT).equals(person.getName().toLowerCase()))
                    .collect(Collectors.toList());
        }else{
            return persons;
        }
    }
}