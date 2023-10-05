package com.codelad.todoapp.utils;

import com.codelad.todoapp.dtos.TagDTO;
import com.codelad.todoapp.models.TagEntity;
import org.springframework.stereotype.Component;

@Component
public class TagUtils {

    public TagDTO convertTagEntityToTagDTO(TagEntity tagEntity){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tagEntity.getId());
        tagDTO.setTagLabel(tagEntity.getTagLabel());
        tagDTO.setColor(tagEntity.getColor());
        return tagDTO;
    }

    public TagEntity convertTagDTOToTagEntity(TagDTO tagDTO){
        TagEntity tagEntity = new TagEntity();
        tagEntity.setId(tagDTO.getId());
        tagEntity.setTagLabel(tagDTO.getTagLabel());
        tagEntity.setColor(tagDTO.getColor());
        return tagEntity;
    }
}
