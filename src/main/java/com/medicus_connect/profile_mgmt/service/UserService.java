package com.medicus_connect.profile_mgmt.service;

import com.medicus_connect.profile_mgmt.model.dtos.Request.CreateUserRequest;
import com.medicus_connect.profile_mgmt.model.entitiles.UserEntity;
import com.medicus_connect.profile_mgmt.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public String createUserAccount(CreateUserRequest request) {

        log.info("Creating user: "+ request.getUserName());
        UserEntity userEntity = new UserEntity();
        userEntity.setUserInfo(request.getUserInfo());
        userEntity.setUserName(request.getUserName());
        userEntity.setPassword(request.getPassword());
        userEntity.setCreatedOn(LocalDateTime.now());
        userEntity.setCreatedBy(request.getUserName());
        userEntity.setLastUpdatedOn(LocalDateTime.now());
        userEntity.setLastUpdatedBy(request.getUserName());
        userRepo.save(userEntity);
        return "ok";
    }
}
