package com.medicus_connect.profile_mgmt.controller;

import com.medicus_connect.profile_mgmt.model.dtos.request.CreateUserRequest;
import com.medicus_connect.profile_mgmt.model.dtos.request.UpdateUserRequest;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetUserResponse;
import com.medicus_connect.profile_mgmt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Api for creating User", description = "")
    @PostMapping("/create")
    public ResponseEntity<String> createUserAccount(@RequestBody CreateUserRequest createUserRequest) {

        log.info("Calling UserService for creating an account");
        return new ResponseEntity<>(userService.createUserAccount(createUserRequest), HttpStatus.OK);
    }

    @Operation(summary = "Api for getting User", description = "")
    @GetMapping("/get-by-mobileNo")
    public ResponseEntity<GetUserResponse> getUserAccount(@RequestParam String mobileNo) {

        log.info("Calling UserService for fetching an account for: {}", mobileNo);
        return new ResponseEntity<>(userService.getUserAccount(mobileNo), HttpStatus.OK);
    }

    @Operation(summary = "Api for updating User", description = "")
    @PutMapping("/update/user")
    public ResponseEntity<String> updateUserAccount(@RequestParam String mobileNo, @RequestBody UpdateUserRequest updateUserRequest) {

        log.info("Calling UserService for updating an account for: {}", mobileNo);
        return new ResponseEntity<>(userService.updateUserAccount(mobileNo, updateUserRequest), HttpStatus.OK);
    }
}
