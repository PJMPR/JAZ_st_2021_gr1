package org.example.queries.rules;

import java.util.List;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public abstract class RulesSet {

    public static void filter(Results results, SearchParameters params, List<RulesEnum> rules) {

        for (RulesEnum rule : rules) {
            switch (rule) {
            case AGETO:
                RuleAgeTo.filterAgeTo(results, params);
                break;
            case AGEFROM:
                RuleAgeFrom.filterAgeFrom(results, params);
                break;
            case GENDER:
                RuleGender.filterGender(results, params);
                break;
            case INCOMETO:
                RuleIncomeTo.filterIncomeTo(results, params);
                break;
            case INCOMEFROM:
                RuleIncomeFrom.filterIncomeFrom(results, params);
                break;
            case NAME:
                RuleName.filterName(results, params);
                break;
            case SURNAME:
                RuleSurname.filterSurname(results, params);
                break;
            case PAGING:
                RulePaging.paging(results, params);
                break;
            default:
                break;
            }
        }

    }

    public static void filter(Results results, SearchParameters params) {

        RuleAgeTo.filterAgeTo(results, params);

        RuleAgeFrom.filterAgeFrom(results, params);

        RuleGender.filterGender(results, params);

        RuleIncomeTo.filterIncomeTo(results, params);

        RuleIncomeFrom.filterIncomeFrom(results, params);

        RuleName.filterName(results, params);

        RuleSurname.filterSurname(results, params);

        RulePaging.paging(results, params);

    }
}
