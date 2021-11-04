package org.example.queries.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class RuleGender extends RulesBase {
    public static void filterGender(Results results, SearchParameters params) {
        if (!params.getSelectedGenders().isEmpty())
            results.setItems(getPersonsStream(results)
                    .filter(x -> (params.getSelectedGenders().contains(x.getGender()))).toList());
    }
}
