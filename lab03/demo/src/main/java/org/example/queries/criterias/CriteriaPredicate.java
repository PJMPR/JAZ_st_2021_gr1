package org.example.queries.criterias;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

@FunctionalInterface
public interface CriteriaPredicate {
    boolean check(Person p, SearchParameters params);
}
