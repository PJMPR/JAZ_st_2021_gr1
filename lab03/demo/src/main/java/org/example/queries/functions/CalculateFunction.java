package org.example.queries.functions;

import org.example.queries.search.Funcs;

import java.util.Arrays;
import java.util.List;

public record CalculateFunction(Funcs funcs) {
    public double calculate(List<Double> list) {
        return isFuncExist(funcs) && !list.isEmpty() ? funcs.execute(list) : 0;
    }

    public boolean isFuncExist(Funcs funcs){
        return Arrays.asList(Funcs.values()).contains(funcs);
    }
}
