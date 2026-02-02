package com.api.toolrental.controller;

import com.api.toolrental.dto.UserDTO;
import com.api.toolrental.model.User;
import com.api.toolrental.service.UserService;
import com.api.toolrental.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")

public class UserController implements GenericController{

    private UserService service;

    private UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        User user = mapper.toUser(dto);
        service.save(user);
        URI location = getHeaderLocation(user.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("id") String id){
        UUID uuid = UUID.fromString(id);
        return service.findById(uuid).map( user -> {
                UserDTO dto = mapper.toDTO(user);
                return ResponseEntity.ok(dto);
        }).orElseGet(() ->ResponseEntity.notFound().build());
    }

   @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUserDetails(){
        List<UserDTO> list = service.findAllUsers().stream().map(user ->
                mapper.toDTO(user))
                .toList();
        return ResponseEntity.ok(list);
   }

   @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UserDTO dto){
        UUID uuid = UUID.fromString(id);
        Optional<User> userOptional = service.findById(uuid);

        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var user = userOptional.get();
        user.setName(dto.name());
        user.setEmail(dto.email());

        service.update(user);

        return ResponseEntity.noContent().build();
   }

   @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id){
        UUID uuid = UUID.fromString(id);
        var user = service.findById(uuid);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.delete(user.get());
        return ResponseEntity.noContent().build();
   }

}
