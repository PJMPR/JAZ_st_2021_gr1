package org.example.queries.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public interface Criteria {
    void verificator (Results results, SearchParameters searchParameters);
}
