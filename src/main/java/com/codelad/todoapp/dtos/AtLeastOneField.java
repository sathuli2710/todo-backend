package com.codelad.todoapp.dtos;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneFieldValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface AtLeastOneField {
    String message() default "At Lease one field should be passed to update the task";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
