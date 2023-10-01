package com.codelad.todoapp.utils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToTimestampConvertor extends PropertyEditorSupport {
    @Override
    public void setAsText(String stringDateTime) throws IllegalArgumentException{
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDateTime = dateFormat.parse(stringDateTime);
            Timestamp timestamp = new Timestamp(parsedDateTime.getTime());
            setValue(timestamp);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Invalid Date Format");
        }
    }
}
