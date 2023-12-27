package com.codelad.todoapp.utils;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringToIntegerListConvertor extends PropertyEditorSupport {
    @Override
    public void setAsText(String stringList) throws IllegalArgumentException{
        try{
            List<Integer> intergerList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(stringList);
            while(matcher.find()){
                Integer number = Integer.parseInt(matcher.group());
                intergerList.add(number);
            }
            setValue(intergerList);
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid Integer List format");
        }
    }
}
