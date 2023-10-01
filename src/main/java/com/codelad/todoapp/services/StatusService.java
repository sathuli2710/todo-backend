package com.codelad.todoapp.services;

import com.codelad.todoapp.Repositories.StatusRepository;
import com.codelad.todoapp.dtos.StatusDTO;
import com.codelad.todoapp.models.StatusEntity;
import com.codelad.todoapp.utils.StatusUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    @Autowired
    StatusUtils statusUtils;


    public List<StatusDTO> getAllStatus(){
        try{
            List<StatusEntity> allStatusEntities = statusRepository.findAll();
            List<StatusDTO> allStatusDtos = new ArrayList<>();
            for(StatusEntity statusEntity : allStatusEntities){
                allStatusDtos.add(statusUtils.convertStatusEntityToStatusDTO(statusEntity));
            }
            return allStatusDtos;
        }
        catch(Exception ignored){
            return null;
        }
    }

    public StatusDTO getStatusById(String statusId){
        try{
            StatusEntity statusEntity = statusRepository.findById(Integer.parseInt(statusId)).orElse(null);
            if(Objects.nonNull(statusEntity)){
                return statusUtils.convertStatusEntityToStatusDTO(statusEntity);
            }
            return null;
        }catch (Exception ignored){
            return null;
        }
    }
}
