package com.api.toolrental.dto;

import com.api.toolrental.model.RentalStatus;
import com.api.toolrental.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RentalDTO(
        UUID id,
        UUID idUser,
        //private List<Tool> tools;
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal totalPrice,
        RentalStatus status
){
}
