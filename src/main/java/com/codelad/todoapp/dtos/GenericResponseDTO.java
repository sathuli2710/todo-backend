package com.codelad.todoapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponseDTO<T> {
    private int statusCode;
    private String message;
    private T data;
    private T error;
}
