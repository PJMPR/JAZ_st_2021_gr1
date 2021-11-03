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
        if(gender != null) return persons.stream().filter(person -> person.getGender().equals(gender)).toList();
        return persons;
    }
}
