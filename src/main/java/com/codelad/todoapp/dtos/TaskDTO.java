package com.codelad.todoapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AtLeastOneField(groups = UpdationValidator.class)
public class TaskDTO {
    private Long id;
    @NotNull(message = "Title should be passed", groups = CreationValidator.class)
    @NotBlank(message = "Title Should not be Empty", groups = CreationValidator.class)
    private String title;
    private String subTitle;
    private String content;
    @Positive(message = "statusId should be positive", groups = CreationValidator.class)
    private Integer statusId;
    private List<Long> tagIds;
    private Timestamp dueDateTime;
    private Timestamp creationDateTime;
    @NotNull(message = "Creator should be passed", groups = CreationValidator.class)
    @NotBlank(message = "Creator Should not be Empty", groups = CreationValidator.class)
    private String creator;
    private Timestamp updatedDatetime;
    @NotNull(message = "Updater should be passed", groups = UpdationValidator.class)
    @NotBlank(message = "Updater Should not be Empty", groups = UpdationValidator.class)
    private String updater;
}
