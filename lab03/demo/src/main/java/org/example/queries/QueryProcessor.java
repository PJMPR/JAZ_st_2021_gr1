package org.example.queries;

<<<<<<< HEAD
import org.example.function.Functions;
import org.example.filter.*;
import org.example.model.People;
=======
import org.example.model.People;
import org.example.queries.calculations.Calculate;
import org.example.queries.calculations.Calculator;
import org.example.queries.criterias.Criteria;
import org.example.queries.criterias.SimpleCriteria;
import org.example.queries.paging.Pager;
import org.example.queries.paging.Paging;
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {
<<<<<<< HEAD
    List<Filter> filters = List.of(
            new FromAge(),
            new ToAge(),
            new Gender(),
            new FromIncome(),
            new ToIncome(),
            new Surname(),
            new Name(),
            new Page()
=======

    List<QueryResultsProcessor> queryResultsProcessors = List.of(
            new SimpleCriteria(p->p.getAgeFrom()>0, (person, params)->person.getAge()>params.getAgeFrom()),
            new Pager(),
            new Calculator()
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
<<<<<<< HEAD

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));

        Functions functions = new Functions();
        functions.applyFunctions(result,parameters);

        return result;
    }
}
=======
        result.setItems(People.Data);
        queryResultsProcessors.stream().filter(x-> x instanceof Criteria).forEach(criteria->criteria.process(result));
        queryResultsProcessors.stream().filter(x->x instanceof Calculate).forEach(calculation->calculation.process(result));
        queryResultsProcessors.stream().filter(x->x instanceof Paging).forEach(paging->paging.process(result));


        return result;
    }
}
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
