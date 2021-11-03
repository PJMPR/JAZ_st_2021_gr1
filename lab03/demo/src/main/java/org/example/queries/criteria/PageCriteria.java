package org.example.queries.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class PageCriteria implements Criteria{
    @Override
    public void verificator(Results results, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null) {

            results.setPages(calculatePages(results, searchParameters));
            results.setCurrentPage(searchParameters.getPage().getPageNumber());
            resultVerificator(results, searchParameters);

        }
    }

    private void resultVerificator(Results results, SearchParameters searchParameters) {
        results.setItems(results
                .getItems()
                .stream()
                .skip(calculateElement(results, searchParameters))
                .limit(searchParameters.getPage().getSize())
                .collect(Collectors.toList()));
    }

    public int calculateElement(Results results, SearchParameters searchParameters) {
        int numOfPages = results.getCurrentPage();
        int numOfElementsOnPage = searchParameters.getPage().getSize();

        return numOfElementsOnPage * (numOfPages - 1);
    }

    public int calculatePages(Results result, SearchParameters searchParameters) {
        int numOfElements = result.getItems().size();
        int maxElementsOnPage = searchParameters.getPage().getSize();

        if (maxElementsOnPage < numOfElements) {
            if (numOfElements % maxElementsOnPage == 0) {
                return numOfElements / maxElementsOnPage ;
            } else {
                return numOfElements / maxElementsOnPage + 1;
            }
        }
        return 1;
    }
}
