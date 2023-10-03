package com.codelad.todoapp.controllers;

import com.codelad.todoapp.dtos.*;
import com.codelad.todoapp.services.StatusService;
import com.codelad.todoapp.services.TaskService;
import com.codelad.todoapp.utils.StringToIntegerConvertor;
import com.codelad.todoapp.utils.StringToTimestampConvertor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    StatusService statusService;

    public record TaskResponse(int numFound, List<TaskDTO> data){}
    @GetMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> getAllTasks(){
        try{
            List<TaskDTO> allTasks = taskService.getAllTasks();
            TaskResponse taskResponse = new TaskResponse(allTasks.size(), allTasks);
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", taskResponse, null), HttpStatus.OK);
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
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", new ArrayList<>(), "No Such Task"), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot get the Task"), HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Timestamp.class, new StringToTimestampConvertor());
        binder.registerCustomEditor(Integer.class, new StringToIntegerConvertor());
    }

    @PostMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> createTask(@Validated(CreationValidator.class) @ModelAttribute TaskDTO taskDto, BindingResult bindingResult){
        if(taskDto.getStatusId() != null) {
            taskDto.setStatusId(1);
        }
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), bindingResult.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try{
            StatusDTO statusDTO = statusService.getStatusById(taskDto.getStatusId().toString());
            if(Objects.nonNull(statusDTO)){
                taskService.createTask(taskDto);
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.CREATED.value(), "SUCCESS", "Successfully Created the Task", null), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Please use proper statusId"), HttpStatus.BAD_REQUEST);
        }catch(Exception ignored) {
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot create the task"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<GenericResponseDTO<?>> updateTask(@NonNull @PathVariable String taskId, @Validated(UpdationValidator.class) @ModelAttribute TaskDTO updateTaskDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), bindingResult.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try{
            TaskDTO existingTaskDto = taskService.getTaskById(taskId);
            if(Objects.nonNull(existingTaskDto)) {
                if(updateTaskDto.getStatusId() != null){
                    StatusDTO statusDTO = statusService.getStatusById(updateTaskDto.getStatusId().toString());
                    if(Objects.nonNull(statusDTO)){
                        taskService.updateTask(existingTaskDto, updateTaskDto);
                        return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", "Successfully Updated the Task", null), HttpStatus.OK);
                    }
                    return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Please use proper statusId"), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", "No Such Task", null), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot update the task"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<GenericResponseDTO<?>> deleteById(@NonNull @PathVariable String taskId){
        try{
            TaskDTO existingTaskDto = taskService.getTaskById(taskId);
            if(Objects.nonNull(existingTaskDto)) {
                taskService.deleteById(taskId);
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", "Successfully Deleted the Task", null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", "No Such Task", null), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot delete the task"), HttpStatus.BAD_REQUEST);
        }
    }

}
