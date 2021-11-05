package org.example.queries;

import org.example.criteria.Criteria;
import org.example.criteria.filters.*;
import org.example.criteria.operations.AndCriteria;
import org.example.criteria.operations.OrCriteria;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class FilterProcessor {
    SearchParameters parameters;

    public FilterProcessor(SearchParameters parameters) {
        this.parameters = parameters;
    }

    public void filterData(Results result) {
        getCriteria().forEach(c -> {
            result.setItems(c.meetCriteria(result.getItems()));
        });
    }

    private List<Criteria> getCriteria(){
        return List.of(
                new OrCriteria(getGenderCriteria()),
                new AndCriteria( List.of(
                        new IncomeFromCriteria(parameters.getIncomeFrom()),
                        new AgeFromCriteria(parameters.getAgeFrom()),
                        new IncomeToCriteria(parameters.getIncomeTo()),
                        new AgeToCriteria(parameters.getAgeTo()),
                        new NameCriteria(parameters.getName()),
                        new SerNameCriteria(parameters.getSurname())
                ))
        );
    }

    private List<Criteria> getGenderCriteria(){
        return parameters.getSelectedGenders().stream().map(GenderCriteria::new).collect(Collectors.toList());
    }
}
