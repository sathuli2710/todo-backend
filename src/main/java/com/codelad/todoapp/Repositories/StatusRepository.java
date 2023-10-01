package com.codelad.todoapp.Repositories;

import com.codelad.todoapp.models.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
}