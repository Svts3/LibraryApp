package com.example.libraryapp.mapper;

import com.example.libraryapp.model.Book;
import org.mapstruct.*;

@Mapper
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBook(@MappingTarget Book book, Book updatedBook);

}
