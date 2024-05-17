package com.example.libraryapp.mapper;

import com.example.libraryapp.model.User;
import org.mapstruct.*;

@Mapper
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, User updatedUser);
}
