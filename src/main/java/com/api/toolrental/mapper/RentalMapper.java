package com.api.toolrental.mapper;

import com.api.toolrental.dto.RentalDTO;
import com.api.toolrental.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    Rental toRental(RentalDTO dto);

    @Mapping(target="idUser",source="user.id")
    RentalDTO toDTO(Rental rental);
}
