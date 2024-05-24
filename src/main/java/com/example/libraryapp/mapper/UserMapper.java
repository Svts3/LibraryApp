package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.UserDTO;
import com.example.libraryapp.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, User updatedUser);

    UserDTO userToUserDTO(User user);
    List<UserDTO> usersToUserDTOs(List<User> users);
}
