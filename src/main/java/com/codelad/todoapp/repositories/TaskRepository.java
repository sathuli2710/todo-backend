package com.codelad.todoapp.repositories;

import com.codelad.todoapp.models.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
