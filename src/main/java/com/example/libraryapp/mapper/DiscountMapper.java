package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.DiscountDTO;
import com.example.libraryapp.model.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper( DiscountMapper.class );

    DiscountDTO discountToDiscountDTO(Discount discount);

    List<DiscountDTO> discountsToDiscountDTOs(List<Discount>discounts);
}
