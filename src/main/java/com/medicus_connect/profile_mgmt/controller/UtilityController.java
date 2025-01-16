package com.medicus_connect.profile_mgmt.controller;

import com.medicus_connect.profile_mgmt.model.dtos.response.GetDoctorResponse;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetUserResponse;
import com.medicus_connect.profile_mgmt.service.DoctorService;
import com.medicus_connect.profile_mgmt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    //====================== User Controller ============================
    @Operation(summary = "Api for getting user by userId", description = "")
    @GetMapping("/get-by-userid")
    public ResponseEntity<GetUserResponse> getUserAccount(@RequestParam String userId) {

        log.info("Calling UserService for fetching an account for: {}", userId);
        return new ResponseEntity<>(userService.getUserAccount(false, null, userId), HttpStatus.OK);
    }

    //====================== Doctor Controller ============================
    @Operation(summary = "Api for getting doctor by doctorId", description = "")
    @GetMapping("/get-by-doctorid")
    public ResponseEntity<GetDoctorResponse> getDoctorAccount(@RequestParam String doctorId) {

        log.info("Calling DoctorService for fetching an account for: {}", doctorId);
        return new ResponseEntity<>(doctorService.getDoctorAccount(false, null, doctorId), HttpStatus.OK);
    }
}
