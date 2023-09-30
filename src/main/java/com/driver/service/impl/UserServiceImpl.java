package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;
    public UserDto createUser(UserDetailsRequestModel userDetails) {

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(String.valueOf(UUID.randomUUID()));
        userEntity.setFirstName(userDetails.getFirstName());
        userEntity.setLastName(userDetails.getLastName());
        userEntity.setEmail(userDetails.getEmail());

        UserEntity savedUser = userRepository.save(userEntity);

        UserDto response = new UserDto();

        response.setId(savedUser.getId());
        response.setUserId(savedUser.getUserId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    public UserDto getUserByUserId(String id) throws Exception {

        UserEntity user = userRepository.findByUserId(id);

        if(user == null)
            throw new Exception("User Not Found");

        UserDto response = new UserDto();

        response.setId(user.getId());
        response.setUserId(user.getUserId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());

        return response;
    }

    public UserDto updateUser(String id, UserDetailsRequestModel userDetails) throws Exception {

        UserEntity user = userRepository.findByUserId(id);

        if(user == null)
            throw new Exception("User Not Found");
        UserDto response = new UserDto();

        response.setUserId(id);
        response.setFirstName(userDetails.getFirstName());
        response.setLastName(userDetails.getLastName());
        response.setEmail(userDetails.getEmail());

        return response;
    }

    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        userRepository.delete(userEntity);
    }

    public List<UserDto> getUsers() {
        Iterable<UserEntity> users = userRepository.findAll();

        List<UserDto> ans = new ArrayList<>();

        for(UserEntity userEntity : users){

            UserDto response = new UserDto();

            response.setUserId(userEntity.getUserId());
            response.setFirstName(userEntity.getFirstName());
            response.setId(userEntity.getId());
            response.setLastName(userEntity.getLastName());
            response.setEmail(userEntity.getEmail());

            ans.add(response);
        }
        return ans;
    }
}