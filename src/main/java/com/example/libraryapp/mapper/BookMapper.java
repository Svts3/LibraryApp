package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.model.Book;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBook(@MappingTarget Book book, Book updatedBook);

    BookDTO bookToBookDTO(Book book);

    List<BookDTO>booksToBookDTOs(List<Book>books);

}
