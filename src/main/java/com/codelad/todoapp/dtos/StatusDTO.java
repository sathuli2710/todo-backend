package com.codelad.todoapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private Integer id;
    private String statusLabel;
    private Character shortCode;
    private String color;
}
