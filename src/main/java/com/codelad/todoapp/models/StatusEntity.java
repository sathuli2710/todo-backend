package com.codelad.todoapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "status_info")
@Data
public class StatusEntity {
    @Id
    private Integer id;
    @Column(name = "status_label")
    private String statusLabel;
    @Column(name = "short_code")
    private Character shortCode;
    private String color;
}
