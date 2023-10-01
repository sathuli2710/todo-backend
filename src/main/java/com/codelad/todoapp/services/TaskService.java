package com.codelad.todoapp.services;

import com.codelad.todoapp.Repositories.TaskRepository;
import com.codelad.todoapp.dtos.TaskDTO;
import com.codelad.todoapp.models.TaskEntity;
import com.codelad.todoapp.utils.TaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class TaskService {

    @Autowired
    TaskUtils taskUtils;

    @Autowired
    TaskRepository taskRepository;
    public void createTask(TaskDTO taskDto){
        try{
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());
            TaskEntity taskEntity = taskUtils.convertTaskDTOtoTaskEntity(taskDto);
            taskEntity.setCreationDateTime(currentTimestamp);
            taskEntity.setUpdatedDatetime(currentTimestamp);
            taskRepository.save(taskEntity);
        }
        catch(Exception ignored){}
    }

    public List<TaskDTO> getAllTasks(){
        try{
            List<TaskEntity> allTaskEntities = taskRepository.findAll();
            List<TaskDTO> allTaskDtos = new ArrayList<TaskDTO>();
            for(TaskEntity taskEntity : allTaskEntities){
                TaskDTO taskDto = taskUtils.convertTaskEntityToTaskDTO(taskEntity);
                allTaskDtos.add(taskDto);
            }
            return allTaskDtos;
        }catch(Exception ignored){
            return null;
        }
    }

    public TaskDTO getTaskById(String taskId){
        try{
            TaskEntity taskEntity = taskRepository.findById(Long.parseLong(taskId)).orElse(null);
            if(Objects.nonNull(taskEntity)){
                return taskUtils.convertTaskEntityToTaskDTO(taskEntity);
            }
            return null;
        }catch(Exception ignored){
            return null;
        }
    }
}
