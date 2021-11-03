package org.example.queries.rules;

import java.util.Arrays;
import java.util.List;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;


public class RuleSet implements IRules {
    private Results results;
    private SearchParameters params;
    List<Object> ruleSet = List.of(
        new RuleAge(results,params),
        new RuleGender(results,params),
        new RuleIncome(results,params),
        new RuleName(results,params),
        new RuleSurname(results,params)
    );

    public RuleSet(Results results, SearchParameters params){
        this.results = results;
        this.params = params;
    }
}
