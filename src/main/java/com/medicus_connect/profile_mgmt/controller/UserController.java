package com.medicus_connect.profile_mgmt.controller;

import com.medicus_connect.profile_mgmt.model.dtos.Request.CreateUserRequest;
import com.medicus_connect.profile_mgmt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Api for creating User Controller", description = "")
    @PostMapping("/create/user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {

        log.info("Calling UserService for creating an account");
        return new ResponseEntity<>(userService.createUserAccount(createUserRequest), HttpStatus.OK);
    }
}
