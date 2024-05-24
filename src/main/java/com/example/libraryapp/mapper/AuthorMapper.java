package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.model.Author;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper( AuthorMapper.class );

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAuthor(@MappingTarget Author author, Author updatedAuthor);

    AuthorDTO authorToAuthorDTO(Author user);

    List<AuthorDTO>authorsToAuthorDTOs(List<Author>authors);
}
