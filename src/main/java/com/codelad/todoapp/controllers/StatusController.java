package com.codelad.todoapp.controllers;

import com.codelad.todoapp.dtos.CreationValidator;
import com.codelad.todoapp.dtos.GenericResponseDTO;
import com.codelad.todoapp.dtos.StatusDTO;
import com.codelad.todoapp.dtos.UpdationValidator;
import com.codelad.todoapp.services.StatusService;
import com.codelad.todoapp.utils.StringToCharacterConvertor;
import com.codelad.todoapp.utils.StringToIntegerConvertor;
import com.codelad.todoapp.utils.StringToLongConvertor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Long.class, new StringToLongConvertor());
        binder.registerCustomEditor(char.class, new StringToCharacterConvertor());
        binder.registerCustomEditor(Integer.class, new StringToIntegerConvertor());
    }

    @PostMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> createStatus(@Validated(CreationValidator.class) @ModelAttribute StatusDTO statusDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), bindingResult.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try{
            List<StatusDTO> allStatus = statusService.getAllStatus();
            for(StatusDTO existingStatusDTO : allStatus){
                if(existingStatusDTO.getId().toString().equalsIgnoreCase(statusDTO.getId().toString())){
                    return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Id cannot be duplicate"), HttpStatus.BAD_REQUEST);
                }
            }
            statusService.createStatus(statusDTO);
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.CREATED.value(), "SUCCESS", "Successfully created the status", null), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Could not create status"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{statusId}")
    public ResponseEntity<GenericResponseDTO<?>> updateStatus(@NonNull @PathVariable String statusId, @Validated(UpdationValidator.class) @ModelAttribute StatusDTO updateStatusDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), bindingResult.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try{
            StatusDTO existingStatusDto = statusService.getStatusById(statusId);
            if(Objects.nonNull(existingStatusDto)){
                statusService.updateStatus(existingStatusDto, updateStatusDto);
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", "Successfully updated the status", null), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", new ArrayList<>(), "No Such Status"), HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Could not update status"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{statusId}")
    public ResponseEntity<GenericResponseDTO<?>> deleteStatusById(@NonNull @PathVariable String statusId){
        try{
            StatusDTO existingStatusDto = statusService.getStatusById(statusId);
            if(Objects.nonNull(existingStatusDto)){
                statusService.deleteStatusById(statusId);
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", "Successfully deleted the status", null), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", new ArrayList<>(), "No Such Status"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Could not delete status"), HttpStatus.BAD_REQUEST);
        }
    }
}
