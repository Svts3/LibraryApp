package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.UserReturnDTO;
import com.example.libraryapp.model.UserReturn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserReturnMapper {

    UserReturnMapper INSTANCE = Mappers.getMapper(UserReturnMapper.class);

    UserReturnDTO userReturnToUserReturnDTO(UserReturn userReturn);

    List<UserReturnDTO> userReturnsToUserReturnDTOS(List<UserReturn> userReturn);


}
