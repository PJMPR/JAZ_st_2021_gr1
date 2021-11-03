package org.example.queries;

import org.example.filter_rules.*;
import org.example.functions.AgeIncomeMethods;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;


public class QueryProcessor {
    List<FilterInterface> filterInterfaceList = List.of(
            new FromAgeRule(),
            new FromIncRule(),
            new GenderRule(),
            new NameRule(),
            new SurnameRule(),
            new ToAgeRule(),
            new ToIncRule(),
            new PageSet()
    );

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();


        AgeIncomeMethods function = new AgeIncomeMethods();
        result.setItems(People.Data);

        filterInterfaceList.forEach(filter -> filter.useFilterInterface(result, parameters));

        function.ageIncomeFunction(parameters, result);

        return result;
    }
}
