package org.example.filter_rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface FilterInterface {
    void useFilterInterface(Results results, SearchParameters searchParameters);
}
