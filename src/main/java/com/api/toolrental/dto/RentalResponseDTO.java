package com.api.toolrental.dto;

import com.api.toolrental.model.RentalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RentalResponseDTO (
        UUID id,
        //private List<Tool> tools;
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal totalPrice,
        RentalStatus status

){
}
