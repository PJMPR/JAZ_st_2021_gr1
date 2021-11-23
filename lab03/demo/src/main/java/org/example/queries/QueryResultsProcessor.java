package org.example.queries;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface QueryResultsProcessor {
    void setParameters(SearchParameters parameters);
    void process(Results results);
}
