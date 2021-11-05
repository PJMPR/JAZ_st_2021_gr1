package org.example.criteria.filters;

import org.example.criteria.Criteria;
import org.example.model.Gender;
import org.example.model.Person;

import java.util.List;

public class GenderCriteria implements Criteria {
    Gender gender;

    public GenderCriteria(Gender gender){
        this.gender = gender;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return (gender == null) ? persons : persons.stream()
                .filter(person -> person.getGender().equals(gender))
                .toList();
    }
}
