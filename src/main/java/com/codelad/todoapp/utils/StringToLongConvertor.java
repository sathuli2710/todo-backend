package com.codelad.todoapp.utils;

import java.beans.PropertyEditorSupport;

public class StringToLongConvertor extends PropertyEditorSupport {

    @Override
    public void setAsText(String stringInput) throws IllegalArgumentException{
        try{
            setValue(Long.parseLong(stringInput));
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid Long Input Format");
        }
    }

}
