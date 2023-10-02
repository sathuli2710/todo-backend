package com.codelad.todoapp.utils;

import java.beans.PropertyEditorSupport;

public class StringToCharacterConvertor extends PropertyEditorSupport {
    @Override
    public void setAsText(String stringInput) throws IllegalArgumentException{
        try{
            setValue(stringInput.charAt(0));
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid Character Input Format");
        }
    }
}
