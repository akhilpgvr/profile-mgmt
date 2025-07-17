package com.medicus_connect.profile_mgmt.controller;

import com.medicus_connect.profile_mgmt.model.dtos.request.DocSlotRequest;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetSlotsResponse;
import com.medicus_connect.profile_mgmt.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/slot")
public class DocSlotController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "", description = "")
    @PostMapping("/add")
    public ResponseEntity<String> addDocSlot(@RequestParam String mobileNo, @RequestBody DocSlotRequest docSlotRequest) {

        docSlotRequest.setMobileNo(mobileNo);
        log.info("Calling doctorService to adding a new slot for {}", mobileNo);
        return new ResponseEntity<>(doctorService.addDocSlot(docSlotRequest), HttpStatus.OK);
    }

    @Operation(summary = "Api to get appointments of a doctor for the given month", description = "")
    @GetMapping("/get/month")
    public ResponseEntity<List<GetSlotsResponse>> getSlotOfMonth(@RequestParam String mobileNo, @RequestParam int month) {

        log.info("Calling doctorService to get all slot for {}", mobileNo);
        return new ResponseEntity<>(doctorService.getSlotOfMonth(mobileNo, month), HttpStatus.OK);
    }
}
