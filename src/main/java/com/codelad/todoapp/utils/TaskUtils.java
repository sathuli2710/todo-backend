package com.codelad.todoapp.utils;

import com.codelad.todoapp.dtos.TaskDTO;
import com.codelad.todoapp.models.TaskEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskUtils {

//    private static final Logger logger = LoggerFactory.getLogger(TaskUtils.class);
    public Timestamp ConvertStringtoTimestamp(String stringDateTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date parsedDateTime = dateFormat.parse(stringDateTime);
            return new Timestamp(parsedDateTime.getTime());
        }
        catch(ParseException ignored){
            return null;
        }
    }

    public Timestamp ConvertUnixTimeStamptoTimestamp(String unixTimestamp){
        try{
            long unixLongTimeStamp = Long.parseLong(unixTimestamp);
            return new Timestamp(unixLongTimeStamp * 1000L);
        }
        catch(NumberFormatException ignored){
            return null;
        }
    }

    public TaskDTO convertTaskEntityToTaskDTO(TaskEntity taskEntity){
        TaskDTO taskDto = new TaskDTO();
        taskDto.setId(taskEntity.getId());
        taskDto.setTitle(taskEntity.getTitle());
        taskDto.setSubTitle(taskEntity.getSubTitle());
        taskDto.setContent(taskEntity.getContent());
        taskDto.setStatus(taskEntity.getStatus());
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
        taskEntity.setStatus(taskDto.getStatus());
        taskEntity.setDueDateTime(taskDto.getDueDateTime());
        taskEntity.setCreationDateTime(taskDto.getCreationDateTime());
        taskEntity.setCreator(taskDto.getCreator());
        taskEntity.setUpdatedDatetime(taskDto.getUpdatedDatetime());
        taskEntity.setUpdater(taskDto.getUpdater());
        return taskEntity;
    }

}
