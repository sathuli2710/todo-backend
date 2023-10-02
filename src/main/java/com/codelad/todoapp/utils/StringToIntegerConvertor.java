package com.codelad.todoapp.utils;

import java.beans.PropertyEditorSupport;

public class StringToIntegerConvertor extends PropertyEditorSupport {
    @Override
    public void setAsText(String stringInput) throws IllegalArgumentException{
        try{
            setValue(Integer.parseInt(stringInput));
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid Integer Input Format");
        }
    }
}
