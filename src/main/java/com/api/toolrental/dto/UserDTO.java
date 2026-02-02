package com.api.toolrental.dto;

import com.api.toolrental.model.Rental;

import java.util.List;

public record UserDTO(
        String id,
        String name,
        String email,
        List<RentalResponseDTO> rentals){
}
