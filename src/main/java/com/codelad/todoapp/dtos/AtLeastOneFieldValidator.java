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
        if(value instanceof StatusDTO) {
            StatusDTO statusDTO = (StatusDTO) value;
            return statusDTO.getStatusLabel() != null || statusDTO.getShortCode() != null || statusDTO.getColor() != null;
        }
        if(value instanceof TagDTO){
            TagDTO tagDTO = (TagDTO) value;
            return tagDTO.getTagLabel() != null || tagDTO.getColor() != null;
        }
        return true;
    }
}
