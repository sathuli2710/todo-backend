package com.codelad.todoapp.controllers;

import com.codelad.todoapp.dtos.*;
import com.codelad.todoapp.services.TagService;
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
@RequestMapping("api/v1/tags")
public class TagController {
    @Autowired
    TagService tagService;

    public record TagResponse(int numFound, List<TagDTO> data){}

    @GetMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> getAllTags(){
        try{
            List<TagDTO> allTags = tagService.getAllTags();
            TagResponse tagResponse = new TagController.TagResponse(allTags.size(), allTags);
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", tagResponse, null), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot get all the tags"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<GenericResponseDTO<?>> getTagById(@NonNull @PathVariable String tagId){
        try{
            TagDTO tagDTO = tagService.getTagById(tagId);
            if(Objects.nonNull(tagDTO)){
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", tagDTO, null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", new ArrayList<>(), "No Such tag"), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Cannot get the tag"), HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Long.class, new StringToLongConvertor());
        binder.registerCustomEditor(char.class, new StringToCharacterConvertor());
        binder.registerCustomEditor(Integer.class, new StringToIntegerConvertor());
    }

    @PostMapping("/")
    public ResponseEntity<GenericResponseDTO<?>> createTag(@Validated(CreationValidator.class) @ModelAttribute TagDTO tagDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), bindingResult.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try{
//            List<TagDTO> allTags = tagService.getAllTags();
//            for(TagDTO existingTagDTO : allTags){
//                if(existingTagDTO.getId().toString().equalsIgnoreCase(tagDTO.getId().toString())){
//                    return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Id cannot be duplicate"), HttpStatus.BAD_REQUEST);
//                }
//            }
            tagService.createTag(tagDTO);
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.CREATED.value(), "SUCCESS", "Successfully created the tag", null), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Could not create tag"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<GenericResponseDTO<?>> updateTag(@NonNull @PathVariable String tagId, @Validated(UpdationValidator.class) @ModelAttribute TagDTO updateTagDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), bindingResult.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try{
            TagDTO existingTagDto = tagService.getTagById(tagId);
            if(Objects.nonNull(existingTagDto)){
                tagService.updateTag(existingTagDto, updateTagDto);
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", "Successfully updated the tag", null), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", new ArrayList<>(), "No Such tag"), HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Could not update tag"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<GenericResponseDTO<?>> deleteTagById(@NonNull @PathVariable String tagId){
        try{
            TagDTO existingTagDto = tagService.getTagById(tagId);
            if(Objects.nonNull(existingTagDto)){
                tagService.deleteTagById(tagId);
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.OK.value(), "SUCCESS", "Successfully deleted the tag", null), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.NOT_FOUND.value(), "ERROR", new ArrayList<>(), "No Such tag"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "ERROR", new ArrayList<>(), "Could not delete tag"), HttpStatus.BAD_REQUEST);
        }
    }

}
