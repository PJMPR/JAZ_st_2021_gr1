package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class NameFilter implements FilterInterface{
    @Override
        public void meetCriteria (Results results, SearchParameters searchParameters){
            if(searchParameters.getName()!=null)
                results.setItems(results.getItems()
                    .stream()
                    .filter(person -> Objects.
                            equals(person.getName().toLowerCase(Locale.ROOT), searchParameters.getName().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList()));
        }
    }

