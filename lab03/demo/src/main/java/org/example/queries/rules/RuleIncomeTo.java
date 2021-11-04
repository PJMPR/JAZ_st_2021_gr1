package org.example.queries.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class RuleIncomeTo extends RulesBase {
    public static void filterIncomeTo(Results results, SearchParameters params) {
        if (params.getIncomeTo() != 0)
            results.setItems(getPersonsStream(results).filter(x -> x.getIncome() <= params.getIncomeTo()).toList());
    }
}
