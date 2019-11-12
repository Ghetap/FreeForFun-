package com.example.freeforfun.freeforfun.business.service;

import com.example.freeforfun.freeforfun.business.dto.UserDto;
import com.example.freeforfun.freeforfun.business.exceptions.BusinessException;
import com.example.freeforfun.freeforfun.business.mapper.UserDtoMapper;
import com.example.freeforfun.freeforfun.persistence.exceptions.RepositoryException;
import com.example.freeforfun.freeforfun.persistence.model.User;
import com.example.freeforfun.freeforfun.persistence.repo.UserDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserService{

    @EJB
    private UserDao userDao;

    public UserDto login(UserDto userDto) throws BusinessException {
//     todo   String encriptedPassword = Hashing.sha256().hashString(userDto.getPassword(), StandardCharsets.UTF_8).toString();
        User user;
        try {
            user = userDao.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        } catch (RepositoryException ex) {
            throw new BusinessException(ex);
        }
        return UserDtoMapper.convertUserToUserDto(user);
    }

    public boolean deleteAccount(UserDto userDto) throws BusinessException{
        boolean isDeleted = false;
        try{
            if(userDao.deleteUserByUsername(userDto.getUsername()).equals(-1))
                isDeleted = true;
        }catch(RepositoryException ex){
            throw new BusinessException(ex);
        }
        return isDeleted;
    }

    public UserDto changePassword(UserDto newUserDto) throws BusinessException{
        User user = UserDtoMapper.convertUserDtoToUser(newUserDto);
        User newUser;
        try {
            newUser = userDao.updateUser(user);
        } catch (RepositoryException ex) {
            throw new BusinessException(ex);
        }
        return UserDtoMapper.convertUserToUserDto(newUser);
    }
}