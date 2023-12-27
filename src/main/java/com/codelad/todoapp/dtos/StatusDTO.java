package com.codelad.todoapp.dtos;

import com.codelad.todoapp.utils.NoNullCharacter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private Integer id;
    @NotNull(message = "status label should be passed", groups = CreationValidator.class)
    @NotBlank(message = "status label should not be empty", groups = CreationValidator.class)
    private String statusLabel;
    @NoNullCharacter(message = "status code should be passed and should not be empty", groups = CreationValidator.class)
    private Character shortCode;
    @NotNull(message = "color should be passed", groups = CreationValidator.class)
    @NotBlank(message = "color should not be empty", groups = CreationValidator.class)
    private String color;
}
