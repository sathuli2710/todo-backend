package com.codelad.todoapp.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoNullCharacterValidator.class)
public @interface NoNullCharacter {
    String message() default  "Character Value should not be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
