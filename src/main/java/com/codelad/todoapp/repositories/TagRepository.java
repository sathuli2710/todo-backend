package com.codelad.todoapp.repositories;

import com.codelad.todoapp.models.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
