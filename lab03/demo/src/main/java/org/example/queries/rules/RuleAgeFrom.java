package org.example.queries.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class RuleAgeFrom extends RulesBase {

    public static void filterAgeFrom(Results results, SearchParameters params) {
        if (params.getAgeFrom() != 0)
            results.setItems(getPersonsStream(results).filter(x -> x.getAge() >= params.getAgeFrom()).toList());
    }

}