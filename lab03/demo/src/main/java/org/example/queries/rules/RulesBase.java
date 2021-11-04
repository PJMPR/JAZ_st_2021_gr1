package org.example.queries.rules;

import org.example.queries.results.Results;

import java.util.stream.Stream;

import org.example.model.Person;

public class RulesBase {

    public static Stream<Person> getPersonsStream(Results results) {
        return results.getItems().stream();
    }
}
