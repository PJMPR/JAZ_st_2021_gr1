package org.example.criteria;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import java.util.List;
import java.util.stream.Collectors;

public class GenderCriteria implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {
        if(!searchParameters.getSelectedGenders().isEmpty()) {
            return persons.stream()
                    .filter(person -> searchParameters.getSelectedGenders().contains(person.getGender()))
                    .collect(Collectors.toList());
        }else{
            return persons;
        }
    }
}