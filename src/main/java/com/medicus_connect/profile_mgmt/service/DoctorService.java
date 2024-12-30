package com.medicus_connect.profile_mgmt.service;

import com.medicus_connect.profile_mgmt.model.dtos.Request.CreateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Request.UpdateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Response.GetDoctorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoctorService {


    public String createDoctorAccount(CreateDoctorRequest createDoctorRequest) {

        return "Account Created";
    }

    public GetDoctorResponse getDoctorAccount(String mobileNo) {
        GetDoctorResponse response = new GetDoctorResponse();

        return response;
    }

    public String updateDoctorAccount(String mobileNo, UpdateDoctorRequest updateDoctorRequest) {

        return "Account Updated";
    }
}
