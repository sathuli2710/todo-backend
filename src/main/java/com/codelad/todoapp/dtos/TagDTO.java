package com.codelad.todoapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AtLeastOneField(groups = UpdationValidator.class, message = "At Lease one field should be passed to update the tag")
public class TagDTO {
    private Long id;
    @NotNull(message = "status label should be passed", groups = CreationValidator.class)
    @NotBlank(message = "status label should not be empty", groups = CreationValidator.class)
    private String tagLabel;
    @NotNull(message = "color should be passed", groups = CreationValidator.class)
    @NotBlank(message = "color should not be empty", groups = CreationValidator.class)
    private String color;
}
