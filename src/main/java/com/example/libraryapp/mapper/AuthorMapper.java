package com.example.libraryapp.mapper;

import com.example.libraryapp.model.Author;
import org.mapstruct.*;

@Mapper
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAuthor(@MappingTarget Author author, Author updatedAuthor);
}
