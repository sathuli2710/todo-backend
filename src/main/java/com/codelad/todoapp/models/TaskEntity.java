package com.codelad.todoapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "sub_title")
    private String subTitle;
    private String content;
    private Integer statusId;
    @Column(name = "due_date_time")
    private Timestamp dueDateTime;
    @Column(name = "creation_date_time", updatable = false)
    private Timestamp creationDateTime;
    private String creator;
    @Column(name = "updated_date_time")
    private Timestamp updatedDatetime;
    private String updater;
}
