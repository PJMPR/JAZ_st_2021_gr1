package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class PageInator implements FilterInterface {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null) {
            results.setCurrentPage(searchParameters.getPage().getPageNumber());
            results.setPages(pageAmount(results, searchParameters));

            results.setItems(
                    results.getItems().
                            stream().
                            skip(pageSkipper(results, searchParameters)).
                            limit(searchParameters.getPage().getSize()).
                            collect(Collectors.toList()));

        }
    }

    private int pageAmount(Results results, SearchParameters searchParameters) {
        int elementsAmount = results.getItems().size();
        int maxElementsAmount = searchParameters.getPage().getSize();

        if (maxElementsAmount < elementsAmount) {
            if (elementsAmount % maxElementsAmount == 0)
                return elementsAmount / maxElementsAmount;
            else
                return elementsAmount / maxElementsAmount + 1;
        } else
            return 1;
    }

    private int pageSkipper(Results results, SearchParameters searchParameters) {
        int pageNumber = results.getCurrentPage();
        int maxElementsAmount = searchParameters.getPage().getSize();

        return (pageNumber - 1) * maxElementsAmount;
    }
}
