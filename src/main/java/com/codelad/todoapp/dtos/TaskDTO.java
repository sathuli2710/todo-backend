package com.codelad.todoapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    @NotNull(message = "Title should be passed")
    @NotBlank(message = "Title Should not be Empty")
    private String title;
    private String subTitle;
    private String content;
    private String status;
    private Timestamp dueDateTime;
    private Timestamp creationDateTime;
    @NotNull(message = "Creator should be passed")
    @NotBlank(message = "Creator Should not be Empty")
    private String creator;
    private Timestamp updatedDatetime;
    @NotNull(message = "Updater should be passed")
    @NotBlank(message = "Updater Should not be Empty")
    private String updater;
}
