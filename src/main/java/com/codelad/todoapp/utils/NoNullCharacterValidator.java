package com.codelad.todoapp.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoNullCharacterValidator implements ConstraintValidator<NoNullCharacter, Character> {
    @Override
    public void initialize(NoNullCharacter constraintAnnotation){}

    @Override
    public boolean isValid(Character value, ConstraintValidatorContext context){
        return value != null && value != '\u0000';
    }

}
