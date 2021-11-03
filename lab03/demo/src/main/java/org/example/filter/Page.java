package org.example.filter;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class Page implements Filter{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getPage()!=null){
            results.setPages(numberOfPages(results, searchParameters));
            results.setCurrentPage(searchParameters.getPage().getPageNumber());

            results.setItems(results.getItems().stream()
                    .skip(elementsToSkip(results, searchParameters))
                    .limit(searchParameters.getPage().getSize())
                    .collect(Collectors.toList()));
        }
    }

    private int elementsToSkip(Results results, SearchParameters searchParameters) {
        int numberOfPages = results.getCurrentPage();
        int numberOfElementsOnPage = searchParameters.getPage().getSize();

        return (numberOfPages - 1) * numberOfElementsOnPage;
    }

    private int numberOfPages(Results results, SearchParameters searchParameters) {
        int numberOfElements = results.getItems().size();
        int maxElementsOnPage = searchParameters.getPage().getSize();

        if (maxElementsOnPage < numberOfElements){
            if (numberOfElements % maxElementsOnPage !=0){
                return numberOfElements / maxElementsOnPage + 1;
            }
            else {
                return numberOfElements / maxElementsOnPage;
            }
        }
        return 1;
    }
}
