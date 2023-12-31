package com.codelad.todoapp.utils;

import com.codelad.todoapp.dtos.TaskDTO;
import com.codelad.todoapp.models.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskUtils {
    public TaskDTO convertTaskEntityToTaskDTO(TaskEntity taskEntity){
        TaskDTO taskDto = new TaskDTO();
        taskDto.setId(taskEntity.getId());
        taskDto.setTitle(taskEntity.getTitle());
        taskDto.setSubTitle(taskEntity.getSubTitle());
        taskDto.setContent(taskEntity.getContent());
        taskDto.setStatusId(taskEntity.getStatusId());
        taskDto.setTagIds(taskEntity.getTagIds());
        taskDto.setDueDateTime(taskEntity.getDueDateTime());
        taskDto.setCreationDateTime(taskEntity.getCreationDateTime());
        taskDto.setCreator(taskEntity.getCreator());
        taskDto.setUpdatedDatetime(taskEntity.getUpdatedDatetime());
        taskDto.setUpdater(taskEntity.getUpdater());
        return taskDto;
    }

    public TaskEntity convertTaskDTOtoTaskEntity(TaskDTO taskDto){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskDto.getId());
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setSubTitle(taskDto.getSubTitle());
        taskEntity.setContent(taskDto.getContent());
        taskEntity.setStatusId(taskDto.getStatusId());
        taskEntity.setTagIds(taskDto.getTagIds());
        taskEntity.setDueDateTime(taskDto.getDueDateTime());
        taskEntity.setCreationDateTime(taskDto.getCreationDateTime());
        taskEntity.setCreator(taskDto.getCreator());
        taskEntity.setUpdatedDatetime(taskDto.getUpdatedDatetime());
        taskEntity.setUpdater(taskDto.getUpdater());
        return taskEntity;
    }
}
