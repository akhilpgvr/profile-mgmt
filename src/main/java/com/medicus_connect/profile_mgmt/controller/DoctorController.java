package com.medicus_connect.profile_mgmt.controller;

import com.medicus_connect.profile_mgmt.model.dtos.request.CreateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.request.UpdateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetDoctorResponse;
import com.medicus_connect.profile_mgmt.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "Api for creating doctor", description = "")
    @PostMapping("/create")
    public ResponseEntity<String> createDoctorAccount(@RequestBody CreateDoctorRequest createDoctorRequest) {

        log.info("Calling DoctorService for creating an account");
        return new ResponseEntity<>(doctorService.createDoctorAccount(createDoctorRequest), HttpStatus.OK);
    }

    @Operation(summary = "Api for getting doctor", description = "")
    @GetMapping("/get-by-mobileNo")
    public ResponseEntity<GetDoctorResponse> getDoctorAccount(@RequestParam String mobileNo) {

        log.info("Calling DoctorService for fetching an account for: {}", mobileNo);
        return new ResponseEntity<>(doctorService.getDoctorAccount(true, mobileNo, null), HttpStatus.OK);
    }

    @Operation(summary = "Api for updating doctor", description = "")
    @PutMapping("/update")
    public ResponseEntity<String> updateDoctorAccount(@RequestParam String mobileNo, @RequestBody UpdateDoctorRequest updateDoctorRequest) {

        log.info("Calling DoctorService for updating an account for: {}", mobileNo);
        return new ResponseEntity<>(doctorService.updateDoctorAccount(mobileNo, updateDoctorRequest), HttpStatus.OK);
    }
}
