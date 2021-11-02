package org.example.validators;


import org.example.validators.rules.NotNullValidationRule;
import org.example.validators.rules.RangeValidationRule;
import org.example.validators.rules.RegexVallidationRule;
import org.example.validators.rules.ValidationRule;

import java.util.List;

public class Validator {

    List<ValidationRule> rules = List.of(
            new NotNullValidationRule(),
            new RangeValidationRule(),
            new RegexVallidationRule()
    );

    public <TClass> ValidationResult validate(TClass object){

        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setValid(true);
        rules.stream().forEach(rule->rule.validateRule(result));
        return result;
    }
}
