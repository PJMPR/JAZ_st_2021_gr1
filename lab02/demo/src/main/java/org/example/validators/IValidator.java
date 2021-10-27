package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

public interface IValidator extends NotNull, Range, Regex {
    public <TClass> ValidationResult validate(TClass object); // moge przez to manipulować wartosciami message

}
