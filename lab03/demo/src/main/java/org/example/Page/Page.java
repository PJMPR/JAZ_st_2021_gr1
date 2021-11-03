package org.example.Page;

import org.example.filters.Criteria;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class Page implements Criteria {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getPage() != null){
            results.setPages(numberOfPages(results,searchParameters));
            results.setCurrentPage(searchParameters.getPage().getPageNumber());

            results.setItems(results.getItems().stream()
                    .skip(elementsToSkip(results, searchParameters))
                    .limit(searchParameters.getPage().getSize())
                    .collect(Collectors.toList()));
        }
    }

    private int numberOfPages(Results results, SearchParameters searchParameters){
        int numberOfElements = results.getItems().size();
        int maxElements = searchParameters.getPage().getSize();

        if(maxElements < numberOfElements){
            if(numberOfElements% maxElements != 0){
                return numberOfElements/maxElements +1;
            }else {
                return numberOfElements/ maxElements;
            }
        }
        return 1;
    }

    private int elementsToSkip(Results results, SearchParameters searchParameters){
        int numberOfPages = results.getCurrentPage();
        int numberOfElementsOnPage = searchParameters.getPage().getSize();

        return (numberOfPages -1) * numberOfElementsOnPage;
    }
}
