package com.codelad.todoapp.controllers;

import com.codelad.todoapp.dtos.GenericResponseDTO;
import com.codelad.todoapp.dtos.StatusDTO;
import com.codelad.todoapp.services.StatusService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    public record StatusResponse(int numFound, List<StatusDTO> data){}

    @GetMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> getAllStatus(){
        try{
            List<StatusDTO> allStatus = statusService.getAllStatus();
            StatusResponse statusResponse = new StatusResponse(allStatus.size(), allStatus);
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", statusResponse, null), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot get all the Status"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{statusId}")
    public ResponseEntity<GenericResponseDTO<?>> getStatusById(@NonNull @PathVariable String statusId){
        try{
            StatusDTO statusDTO = statusService.getStatusById(statusId);
            if(Objects.nonNull(statusDTO)){
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", statusDTO, null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", new ArrayList<>(), "No Such Status"), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot get the Status"), HttpStatus.BAD_REQUEST);
        }
    }
}
