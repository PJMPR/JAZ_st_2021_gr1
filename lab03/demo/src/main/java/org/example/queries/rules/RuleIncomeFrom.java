package org.example.queries.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class RuleIncomeFrom extends RulesBase {
    public static void filterIncomeFrom(Results results, SearchParameters params) {
        if (params.getIncomeFrom() != 0)
            results.setItems(getPersonsStream(results).filter(x -> x.getIncome() >= params.getIncomeFrom()).toList());
    }
}
