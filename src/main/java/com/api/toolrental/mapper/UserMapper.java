package com.api.toolrental.mapper;

import com.api.toolrental.dto.UserDTO;
import com.api.toolrental.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO dto);

    UserDTO toDTO(User user);
}
