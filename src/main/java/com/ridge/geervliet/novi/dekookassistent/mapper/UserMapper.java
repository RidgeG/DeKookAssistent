package com.ridge.geervliet.novi.dekookassistent.mapper;

import com.ridge.geervliet.novi.dekookassistent.dto.Dto.UserDto;
import com.ridge.geervliet.novi.dekookassistent.dto.input.UserInputDto;
import com.ridge.geervliet.novi.dekookassistent.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public User toEntity(UserInputDto inputDto) {
        User user = new User();
        user.setFirstName(inputDto.getFirstName());
        user.setLastName(inputDto.getLastName());
        user.setUsername(inputDto.getUsername());
        user.setPassword(inputDto.getPassword());
        user.setEmail(inputDto.getEmail());
        user.setRole(inputDto.getRole() != null ? inputDto.getRole() : User.Role.USER);
        return user;
    }
}