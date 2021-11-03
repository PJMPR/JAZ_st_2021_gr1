package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class PageFilter implements Filter {
    @Override
    public void useFilters(Results results, SearchParameters searchParameters) {
        if(searchParameters.getPage() != null){
            results.setPages(numberOfPages(results,searchParameters));
            results.setCurrentPage(searchParameters.getPage().getPageNumber());
            results.setItems(results.getItems().stream().skip(elementsToSkip(results, searchParameters)).limit(searchParameters.getPage().getSize()).collect(Collectors.toList()));
        }
    }

    private int numberOfPages(Results results, SearchParameters searchParameters){
        return results.getItems().size() / searchParameters.getPage().getSize()+1;
    }

    private int elementsToSkip(Results results, SearchParameters searchParameters){
        return (results.getCurrentPage() -1) * searchParameters.getPage().getSize();
    }
}