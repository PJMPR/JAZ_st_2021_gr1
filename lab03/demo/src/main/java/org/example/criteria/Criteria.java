package org.example.criteria;

import org.example.model.Person;
import java.util.List;

public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}