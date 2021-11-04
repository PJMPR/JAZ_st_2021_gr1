package org.example.queries;

import java.util.concurrent.atomic.AtomicReference;

import org.example.model.People;
import org.example.queries.functions.Functions;
import org.example.queries.results.Results;
import org.example.queries.rules.RulesSet;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters) {
        AtomicReference<Results> result = new AtomicReference<Results>(new Results());

        result.get().setItems(People.Data);

        RulesSet.filter(result.get(), parameters);
        Functions function = new Functions();
        function.execute(result.get(), parameters);

        return result.get();
    }
}
