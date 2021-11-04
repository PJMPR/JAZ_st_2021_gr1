package org.example.queries.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class RuleAgeTo extends RulesBase {

    public static void filterAgeTo(Results results, SearchParameters params) {
        if (params.getAgeTo() != 0)
            results.setItems(getPersonsStream(results).filter(x -> x.getAge() <= params.getAgeTo()).toList());
    }

}
