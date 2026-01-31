package com.api.toolrental.service;

import com.api.toolrental.model.User;
import com.api.toolrental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void save(User user){
        repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public List<User> findAllUsers(){
        return repository.findAll();
    }

    public User findById(UUID uuid){
        return repository.findById(uuid).orElse(null);
    }
}
