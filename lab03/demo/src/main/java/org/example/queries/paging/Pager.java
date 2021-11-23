package org.example.queries.paging;

import org.example.queries.QueryResultsProcessor;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class Pager implements QueryResultsProcessor, Paging {

    SearchParameters parameters;

    @Override
    public void setParameters(SearchParameters parameters) {
        this.parameters= parameters;
    }

    @Override
    public void process(Results results) {
        int skip = (parameters.getPage().getPageNumber()-1)*parameters.getPage().getSize();
        int take = parameters.getPage().getSize();
        int pages = results.getItems().size()/take;
        if(results.getItems().size()%take!=0)pages++;
        results.setItems(results.getItems().stream().skip(skip).limit(take).collect(Collectors.toList()));
        results.setCurrentPage(parameters.getPage().getPageNumber());
        results.setPages(pages);
    }
}
