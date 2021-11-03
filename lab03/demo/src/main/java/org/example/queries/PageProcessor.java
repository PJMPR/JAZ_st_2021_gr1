package org.example.queries;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PageProcessor {
    private Page page;

    public PageProcessor(Page page){
        this.page = page;
    }

    public void prepareDataToDisplay(Results result) {
        if( page == null || page.getSize() > result.getItems().size()) {
            page = new Page(result.getItems().size(), 1);
        }

        result.setPages(calculatePagesSize(result));
        result.setCurrentPage(page.getPageNumber());
        result.setItems(setSizeToDisplay(result));
    }

    private List<Person> setSizeToDisplay(Results results) {
        if(!results.getItems().isEmpty()){
            int elementsToSkip = page.getSize() * (page.getPageNumber()-1);
            return results.getItems().stream()
                    .skip(elementsToSkip)
                    .limit(page.getSize())
                    .collect(Collectors.toList());
        }
        return results.getItems();
    }

    private int calculatePagesSize(Results result) {
        return result.getItems().size() / page.getSize();
    }
}
