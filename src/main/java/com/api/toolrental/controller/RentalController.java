package com.api.toolrental.controller;

import com.api.toolrental.dto.RentalDTO;
import com.api.toolrental.dto.RentalResponseDTO;
import com.api.toolrental.mapper.RentalMapper;
import com.api.toolrental.model.Rental;
import com.api.toolrental.service.RentalService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("rentals")
public class RentalController implements GenericController {

    private RentalService service;
    private RentalMapper mapper;

    public RentalController(RentalService service, RentalMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO dto){
        service.save(dto);
        URI location = getHeaderLocation(dto.idUser());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<RentalDTO> getRentalDetails(@PathVariable String id){
        UUID uuid= UUID.fromString(id);
        return service.findById(uuid).map(
                rental -> {
                    RentalDTO dto = mapper.toDTO(rental);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () ->ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RentalDTO>> getAllRentals(){
        List<RentalDTO> rentalDTOList = service.findAllRentals().stream()
                .map(rental ->
                    mapper.toDTO(rental)
                ).collect(Collectors.toList());
        return ResponseEntity.ok(rentalDTOList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable String id){
        UUID uuid = UUID.fromString(id);
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateRental(@PathVariable String id, @RequestBody RentalDTO dto){
        UUID uuid = UUID.fromString(id);
        Optional<Rental> rentalOptional = service.findById(uuid);

        if(rentalOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Rental rental = rentalOptional.get();

        rental.setStatus(dto.status());
        rental.setEndDate(dto.endDate());
        rental.setStartDate(dto.startDate());
        rental.setTotalPrice(dto.totalPrice());

        service.update(rental);

        return ResponseEntity.noContent().build();
    }
}
