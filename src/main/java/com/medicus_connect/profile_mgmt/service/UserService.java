package com.medicus_connect.profile_mgmt.service;

import com.medicus_connect.profile_mgmt.exception.UserAlreadyExistsException;
import com.medicus_connect.profile_mgmt.exception.UserNotExistsException;
import com.medicus_connect.profile_mgmt.model.dtos.Request.CreateUserRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Response.GetUserResponse;
import com.medicus_connect.profile_mgmt.model.entitiles.UserEntity;
import com.medicus_connect.profile_mgmt.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity getUser(String mobileNo) {
        Optional<UserEntity> userRef = userRepo.findByMobileNo(mobileNo);
        if(userRef.isPresent()){
            log.info("user present for {}", mobileNo);
            return userRef.get();
        }
        else {
            log.error("user not present for {}", mobileNo);
            throw new UserNotExistsException("User not present for: "+ mobileNo);
        }
    }

    public GetUserResponse getUserAccount(String mobileNo) {

        log.info("fetching user existence for mobile no: {}", mobileNo);
        GetUserResponse response = new GetUserResponse();
        BeanUtils.copyProperties(getUser(mobileNo), response);
        return response;
    }
    public String createUserAccount(CreateUserRequest request) {

        String mobileNo = request.getMobileNo();
        log.info("Checking user existence for mobile no: {}", mobileNo);
        Optional<UserEntity> userRef = userRepo.findByMobileNo(mobileNo);
        if(userRef.isPresent()) {
            throw new UserAlreadyExistsException("User already present for: "+ mobileNo);
        }
        log.info("Creating user: "+ request.getUserName());
        UserEntity userEntity = new UserEntity();
        userEntity.setUserInfo(request.getUserInfo());
        userEntity.setMobileNo(mobileNo);
        userEntity.setUserName(request.getUserName());
        userEntity.setPassword(request.getPassword());
        userEntity.setCreatedOn(LocalDateTime.now());
        userEntity.setCreatedBy(request.getUserName());
        userEntity.setLastUpdatedOn(LocalDateTime.now());
        userEntity.setLastUpdatedBy(request.getUserName());
        userRepo.save(userEntity);
        return "Account Created";
    }
}
