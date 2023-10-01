package com.codelad.todoapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "status_info")
@Data
public class StatusEntity {
    @Id
    @NotNull(message = "id should be passed")
    @NotBlank(message = "id should not be empty")
    private Integer id;
    @NotNull(message = "status label should be passed")
    @NotBlank(message = "status label should not be empty")
    @Column(name = "status_label")
    private String statusLabel;
    @NotNull(message = "status code should be passed")
    @NotBlank(message = "status code should not be empty")
    @Column(name = "short_code")
    private Character shortCode;
    @NotNull(message = "color should be passed")
    @NotBlank(message = "color should not be empty")
    private String color;
}
