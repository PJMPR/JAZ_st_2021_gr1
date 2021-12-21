package org.example.queries.results;

import org.example.queries.search.Funcs;

public class FunctionResult {
    private Funcs function;
    private String fieldName;
    private double value;

<<<<<<< HEAD
=======
    public FunctionResult(){}
    public FunctionResult(String fieldName, Funcs function, double value) {
        this.function = function;
        this.fieldName = fieldName;
        this.value = value;
    }

>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
    public Funcs getFunction() {
        return function;
    }

    public void setFunction(Funcs function) {
        this.function = function;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
