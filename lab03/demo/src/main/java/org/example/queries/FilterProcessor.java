package org.example.queries;

import org.example.criteria.Criteria;
import org.example.criteria.filters.*;
import org.example.criteria.operations.AndCriteria;
import org.example.criteria.operations.OrCriteria;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class FilterProcessor {
    SearchParameters parameters;

    public void filterData(Results result) {
        List<Criteria> criteria  = List.of(
                new OrCriteria(getGenderCriteria()),
                new AndCriteria(new AgeFromCriteria(parameters.getAgeFrom()), new AgeToCriteria(parameters.getAgeTo())),
                new AndCriteria(new IncomeFromCriteria(parameters.getIncomeFrom()), new IncomeToCriteria(parameters.getIncomeTo())),
                new NameCriteria(parameters.getName()),
                new SerNameCriteria(parameters.getSurname())
        );

        criteria.forEach(c -> {
            result.setItems(c.meetCriteria(result.getItems()));
        });
    }

    public FilterProcessor(SearchParameters parameters) {
        this.parameters = parameters;
    }

    private List<Criteria> getGenderCriteria(){
        return parameters.getSelectedGenders().stream().map(GenderCriteria::new).collect(Collectors.toList());
    }
}
