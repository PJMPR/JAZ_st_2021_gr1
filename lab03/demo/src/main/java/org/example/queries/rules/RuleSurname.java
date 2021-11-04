package org.example.queries.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class RuleSurname extends RulesBase {
    public static void filterSurname(Results results, SearchParameters params) {
        if (params.getSurname() != null && params.getSurname() != "")
            results.setItems(getPersonsStream(results).filter(x -> x.getSurname().equalsIgnoreCase(params.getSurname()))
                    .toList());
    }

}
