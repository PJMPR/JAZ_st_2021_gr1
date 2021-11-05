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

    public void prepareDataForDisplay(Results result) {
        page = validatePage(result.getItems());
        result.setPages(calculatePagesSize(result.getItems()));
        result.setCurrentPage(page.getPageNumber());
        result.setItems(setSizeToDisplay(result));
    }

    private List<Person> setSizeToDisplay(Results results) {
        return results.getItems().isEmpty() ? results.getItems() : results.getItems().stream()
                .skip(calculateElementsToSkip())
                .limit(page.getSize())
                .collect(Collectors.toList());
    }

    private int calculatePagesSize(List<Person> list) {
        return (page.getSize() == 0) ? 0 : list.size() / page.getSize();
    }

    private int calculateElementsToSkip() {
        return page.getSize() * (page.getPageNumber()-1);
    }

    private Page validatePage(List<Person> list) {
        return (page == null || page.getSize() > list.size()) ? page = new Page(list.size(), 1) : page;
    }
}