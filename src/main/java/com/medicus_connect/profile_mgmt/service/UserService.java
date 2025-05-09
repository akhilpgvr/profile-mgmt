package com.medicus_connect.profile_mgmt.service;

import com.medicus_connect.profile_mgmt.exception.PasswordMissMatchException;
import com.medicus_connect.profile_mgmt.exception.UserAlreadyExistsException;
import com.medicus_connect.profile_mgmt.exception.UserNotExistsException;
import com.medicus_connect.profile_mgmt.model.dtos.request.CreateUserRequest;
import com.medicus_connect.profile_mgmt.model.dtos.request.UpdateUserRequest;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetDoctorResponse;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetUserResponse;
import com.medicus_connect.profile_mgmt.model.entitiles.UserEntity;
import com.medicus_connect.profile_mgmt.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.medicus_connect.profile_mgmt.Helper.generateUserId;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorService doctorService;

    public UserEntity getUserByMobileNo(String mobileNo) {

        log.info("fetching user account for mobile no: {}", mobileNo);
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
    public UserEntity getUserByUserId(String userId) {

        log.info("fetching user account for userId: {}", userId);
        Optional<UserEntity> userRef = userRepo.findByUserId(userId);
        if(userRef.isPresent()){
            log.info("user present for {}", userId);
            return userRef.get();
        }
        else {
            log.error("user not present for {}", userId);
            throw new UserNotExistsException("User not present for: "+ userId);
        }
    }

    public GetUserResponse getUserAccount(boolean isMobileNo, String mobileNo, String userId) {

        GetUserResponse response = new GetUserResponse();
        if(isMobileNo) BeanUtils.copyProperties(getUserByMobileNo(mobileNo), response);
        else BeanUtils.copyProperties(getUserByUserId(userId), response);
        return response;
    }
    public String createUserAccount(CreateUserRequest request) {

        String mobileNo = request.getMobileNo();
        log.info("checking password similarity for mobile no: {}", mobileNo);
        if(!request.getPassword().equals(request.getReEnteredPassword())) {
            log.error("entered passwords are not similar");
            throw new PasswordMissMatchException("Password MissMatches");
        }
        log.info("Checking user existence for mobile no: {}", mobileNo);
        Optional<UserEntity> userRef = userRepo.findByMobileNo(mobileNo);
        if(userRef.isPresent()) {
            log.error("user exists for the mobile number {}", mobileNo);
            throw new UserAlreadyExistsException("User already present for: "+ mobileNo);
        }
        log.info("Creating user: {}", request.getUserName());
        UserEntity user = new UserEntity();
        user.setUserId(generateUserId(mobileNo));
        user.setUserInfo(request.getUserInfo());
        user.setMobileNo(mobileNo);
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setCreatedOn(LocalDateTime.now());
        user.setCreatedBy(request.getMobileNo());
        user.setLastUpdatedOn(LocalDateTime.now());
        user.setLastUpdatedBy(request.getMobileNo());
        userRepo.save(user);
        return "Account Created";
    }

    public String updateUserAccount(String mobileNo, UpdateUserRequest request) {

        UserEntity user = getUserByMobileNo(mobileNo);
        user.setUserInfo(request.getUserInfo());

        user.setLastUpdatedBy(mobileNo);
        user.setLastUpdatedOn(LocalDateTime.now());
        userRepo.save(user);
        log.info("user account updated for: {}", mobileNo);
        return "Account Updated";
    }

    public List<GetDoctorResponse> getDoctorsList() {

        log.info("calling doctorService to fetch details of all doctors");
        return doctorService.getDoctorList();
    }
}
