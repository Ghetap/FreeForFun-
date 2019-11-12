package com.example.freeforfun.freeforfun.business.mapper;

import com.example.freeforfun.freeforfun.business.dto.UserDto;
import com.example.freeforfun.freeforfun.persistence.model.User;

public class UserDtoMapper {

    public static User convertUserDtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        //TODO user.setRole()...
        return user;
    }

    public static UserDto convertUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setMobileNumber(user.getMobileNumber());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        //TODO userDto.setRole()...
        return userDto;
    }
}
