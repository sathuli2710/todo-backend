package com.codelad.todoapp.utils;

import com.codelad.todoapp.dtos.StatusDTO;
import com.codelad.todoapp.models.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusUtils {
    public StatusDTO convertStatusEntityToStatusDTO(StatusEntity statusEntity){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setId(statusEntity.getId());
        statusDTO.setStatusLabel(statusEntity.getStatusLabel());
        statusDTO.setShortCode(statusEntity.getShortCode());
        statusDTO.setColor(statusEntity.getColor());
        return statusDTO;
    }

    public StatusEntity convertStatusDTOToStatusEntity(StatusDTO statusDTO){
        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setId(statusDTO.getId());
        statusEntity.setStatusLabel(statusDTO.getStatusLabel());
        statusEntity.setShortCode(statusDTO.getShortCode());
        statusEntity.setColor(statusDTO.getColor());
        return statusEntity;
    }
}
