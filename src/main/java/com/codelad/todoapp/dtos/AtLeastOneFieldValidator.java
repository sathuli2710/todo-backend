package com.codelad.todoapp.dtos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneField, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        if(value instanceof TaskDTO) {
            TaskDTO taskDTO = (TaskDTO) value;
            return taskDTO.getTitle() != null || taskDTO.getSubTitle() != null || taskDTO.getContent() != null || taskDTO.getStatusId() != null || taskDTO.getDueDateTime() != null || taskDTO.getTagIds() != null;
        }
        return true;
    }
}
