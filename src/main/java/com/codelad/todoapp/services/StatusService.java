package com.codelad.todoapp.services;

import com.codelad.todoapp.Repositories.StatusRepository;
import com.codelad.todoapp.dtos.StatusDTO;
import com.codelad.todoapp.models.StatusEntity;
import com.codelad.todoapp.utils.StatusUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
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

    public void createStatus(StatusDTO statusDTO){
        try{
            statusRepository.save(statusUtils.convertStatusDTOToStatusEntity(statusDTO));
        }catch(Exception ignored){

        }
    }

    public void updateStatus(StatusDTO existingStatusDto, StatusDTO updateStatusDto){
        try{
            updateStatusDto.setId(updateStatusDto.getId() == null ? existingStatusDto.getId() : updateStatusDto.getId());
            updateStatusDto.setStatusLabel(updateStatusDto.getStatusLabel() == null ? existingStatusDto.getStatusLabel() : updateStatusDto.getStatusLabel());
            updateStatusDto.setShortCode(updateStatusDto.getShortCode() == null ? existingStatusDto.getShortCode() : updateStatusDto.getShortCode());
            updateStatusDto.setColor(updateStatusDto.getColor() == null ? existingStatusDto.getColor() : updateStatusDto.getColor());
            statusRepository.save(statusUtils.convertStatusDTOToStatusEntity(updateStatusDto));
        }
        catch (Exception ignored){}
    }

    public void deleteStatusById(String statusId){
        try{
            statusRepository.deleteById(Integer.parseInt(statusId));
        }catch (Exception ignored){}
    }
}
