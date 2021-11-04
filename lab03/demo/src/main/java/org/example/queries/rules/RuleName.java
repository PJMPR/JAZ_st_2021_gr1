package org.example.queries.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class RuleName extends RulesBase {
    public static void filterName(Results results, SearchParameters params) {
        if (params.getName() != null && params.getName() != "")
            results.setItems(
                    getPersonsStream(results).filter(x -> x.getName().equalsIgnoreCase(params.getName())).toList());
    }
}
