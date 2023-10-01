package com.codelad.todoapp.controllers;

import com.codelad.todoapp.dtos.GenericResponseDTO;
import com.codelad.todoapp.dtos.TaskDTO;
import com.codelad.todoapp.services.TaskService;
import com.codelad.todoapp.utils.StringToTimestampConvertor;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    public record TaskResponse(int numFound, List<TaskDTO> data){};
    @GetMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> getAllTasks(){
        try{
            List<TaskDTO> allTasks = taskService.getAllTasks();
            if(!allTasks.isEmpty()){
                TaskResponse taskResponse = new TaskResponse(allTasks.size(), allTasks);
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", taskResponse, null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "SUCCESS", new ArrayList<>(), null), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot get all the Tasks"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<GenericResponseDTO<?>> getTaskById(@NonNull @PathVariable String taskId){
        try{
            TaskDTO targetTask = taskService.getTaskById(taskId);
            if(Objects.nonNull(targetTask)){
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", targetTask, null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "SUCCESS", new ArrayList<>(), "No Such Task"), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot get the Task"), HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Timestamp.class, new StringToTimestampConvertor());
    }

    @PostMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> createTask(@Valid @ModelAttribute TaskDTO taskDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), bindingResult.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try{
            taskService.createTask(taskDto);
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.CREATED.value(), "SUCCESS", "Successfully Created the Task", null), HttpStatus.CREATED);
        }catch(Exception ignored) {
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot create the task"), HttpStatus.BAD_REQUEST);
        }
    }

}
