package org.example.queries.calculations;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;

import java.lang.reflect.Field;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FieldCalculation implements Calculation{
    FunctionsParameters parameters;
    Funcs funcType;
    private Collector<Number, ?, Double> collector;

    public FieldCalculation(Collector<Number, ?, Double> collector,Funcs funcType) {
        this.funcType = funcType;
        this.collector = collector;
    }

    @Override
    public void setFunctionParameters(FunctionsParameters functionParameters) {
        this.parameters=functionParameters;
    }

    @Override
    public boolean canCalculate() {
        return parameters.getFunction().equals(funcType);
    }

    @Override
    public FunctionResult calculate(Results results) {

        double result = results.getItems()
                .stream()
                .map(x->{
                    try{
                        Field field = x.getClass().getDeclaredField(parameters.getFieldName());
                        field.setAccessible(true);
                        return (Number)x.getClass().getDeclaredField(parameters.getFieldName()).get(x);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    return null;
                })
                .filter(x->x!=null)
                .collect(collector);

        return new FunctionResult(parameters.getFieldName(), parameters.getFunction(), result);
    }
}
