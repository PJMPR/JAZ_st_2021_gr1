package org.example.queries.search;

import java.util.List;
import java.util.stream.Collectors;

public enum Funcs {
    AVARAGE {
        @Override
        public double execute(List<Double> list) {
            return list.stream().collect(Collectors.summarizingDouble(v -> v)).getAverage();
        }
    },
    SUM {
        @Override
        public double execute(List<Double> list) {
            return  list.stream().collect(Collectors.summarizingDouble(v -> v)).getSum();
        }
    };

    public abstract double execute(List<Double> list);
}
