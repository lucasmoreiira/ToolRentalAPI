package com.api.toolrental.service;

import com.api.toolrental.dto.UserDTO;
import com.api.toolrental.model.User;
import com.api.toolrental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user){
        return repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public List<User> findAllUsers(){
        return repository.findAll();
    }

    public Optional<User> findById(UUID uuid){
        return repository.findById(uuid);
    }

    public void update(User user){
      repository.save(user);

    }
}
