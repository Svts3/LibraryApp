package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.UserBorrowDTO;
import com.example.libraryapp.model.UserBorrow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserBorrowMapper {

    UserBorrowMapper INSTANCE= Mappers.getMapper(UserBorrowMapper.class);

    UserBorrowDTO userBorrowToUserBorrowDTO(UserBorrow userBorrow);
    List<UserBorrowDTO> userBorrowsToUserBorrowDTOS(List<UserBorrow> userBorrows);

}
