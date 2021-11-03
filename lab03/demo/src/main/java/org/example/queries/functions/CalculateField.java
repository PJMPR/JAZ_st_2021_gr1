package org.example.queries.functions;

import org.example.queries.search.Funcs;

import java.util.DoubleSummaryStatistics;

public abstract class CalculateField {
    protected DoubleSummaryStatistics summaryStatistics;
    private final String fieldName;

    public CalculateField(String fieldName, DoubleSummaryStatistics summaryStatistics) {
        this.summaryStatistics = summaryStatistics;
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public double calculate(Funcs funcs) {
        switch (funcs){
            case AVARAGE -> {
                return summaryStatistics.getAverage();
            }
            case SUM -> {
                return summaryStatistics.getSum();
            }
        }
        return 0;
    }
}
