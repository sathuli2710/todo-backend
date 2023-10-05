package com.codelad.todoapp.services;

import com.codelad.todoapp.dtos.TagDTO;
import com.codelad.todoapp.models.TagEntity;
import com.codelad.todoapp.repositories.TagRepository;
import com.codelad.todoapp.utils.TagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    TagUtils tagUtils;

    public List<TagDTO> getAllTags(){
        try{
            List<TagEntity> allTagEntities = tagRepository.findAll();
            List<TagDTO> allTagDtos = new ArrayList<>();
            for(TagEntity tagEntity : allTagEntities){
                allTagDtos.add(tagUtils.convertTagEntityToTagDTO(tagEntity));
            }
            return allTagDtos;
        }
        catch(Exception ignored){
            return null;
        }
    }

    public TagDTO getTagById(String tagId){
        try{
            TagEntity tagEntity = tagRepository.findById(Long.parseLong(tagId)).orElse(null);
            if(Objects.nonNull(tagEntity)){
                return tagUtils.convertTagEntityToTagDTO(tagEntity);
            }
            return null;
        }catch (Exception ignored){
            return null;
        }
    }

    public void createTag(TagDTO tagDTO){
        try{
            tagRepository.save(tagUtils.convertTagDTOToTagEntity(tagDTO));
        }catch(Exception ignored){

        }
    }

    public void updateTag(TagDTO existingTagDto, TagDTO updateTagDto){
        try{
            updateTagDto.setId(updateTagDto.getId() == null ? existingTagDto.getId() : updateTagDto.getId());
            updateTagDto.setTagLabel(updateTagDto.getTagLabel() == null ? existingTagDto.getTagLabel() : updateTagDto.getTagLabel());
            updateTagDto.setColor(updateTagDto.getColor() == null ? existingTagDto.getColor() : updateTagDto.getColor());
            tagRepository.save(tagUtils.convertTagDTOToTagEntity(updateTagDto));
        }
        catch (Exception ignored){}
    }

    public void deleteTagById(String tagId){
        try{
            tagRepository.deleteById(Long.parseLong(tagId));
        }catch (Exception ignored){}
    }
}
