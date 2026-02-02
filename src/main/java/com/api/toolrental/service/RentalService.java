package com.api.toolrental.service;

import com.api.toolrental.dto.RentalDTO;
import com.api.toolrental.mapper.RentalMapper;
import com.api.toolrental.model.Rental;
import com.api.toolrental.model.User;
import com.api.toolrental.repository.RentalRepository;
import com.api.toolrental.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentalService {

    private RentalRepository repository;
    private UserRepository userRepository;
    private RentalMapper mapper;

    public RentalService(RentalRepository repository, UserRepository userRepository, RentalMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Rental save(RentalDTO dto){
        Rental rental = mapper.toRental(dto);
        Optional<User> user = userRepository.findById(dto.idUser());
        rental.setUser(user.get());
        return repository.save(rental);
    }

    public Optional<Rental> findById(UUID id){
        return repository.findById(id);
    }

    public List<Rental> findAllRentals(){
        return repository.findAll();
    }

    public void delete(UUID uuid ){
        Optional<Rental> rental = repository.findById(uuid);
        repository.delete(rental.get());
    }

    public void update(Rental rental){
        repository.save(rental);
    }

}
