package com.medicus_connect.profile_mgmt.service;

import com.medicus_connect.profile_mgmt.exception.DoctorAlreadyExistsException;
import com.medicus_connect.profile_mgmt.exception.DoctorNotExistsException;
import com.medicus_connect.profile_mgmt.exception.PasswordMissMatchException;
import com.medicus_connect.profile_mgmt.model.dtos.Request.CreateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Request.UpdateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Response.GetDoctorResponse;
import com.medicus_connect.profile_mgmt.model.entitiles.DoctorEntity;
import com.medicus_connect.profile_mgmt.repo.DoctorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    public DoctorEntity getDoctor(String mobileNo) {

        log.info("fetching doctor account for mobile no: {}", mobileNo);
        Optional<DoctorEntity> doctorRef = doctorRepo.findByMobileNo(mobileNo);
        if(doctorRef.isPresent()){
            log.info("doctor present for {}", mobileNo);
            return doctorRef.get();
        }
        else {
            log.error("doctor not present for {}", mobileNo);
            throw new DoctorNotExistsException("doctor not present for: "+ mobileNo);
        }
    }

    public String createDoctorAccount(CreateDoctorRequest request) {

        String mobileNo = request.getMobileNo();
        log.info("checking password similarity for mobile no: {}", mobileNo);
        if(!request.getPassword().equals(request.getReEnteredPassword())) {
            log.error("entered passwords are not similar");
            throw new PasswordMissMatchException("Password MissMatches");
        }
        log.info("checking mobile no already exists for: {}", mobileNo);
        Optional<DoctorEntity> doctorRef = doctorRepo.findByMobileNo(mobileNo);
        if(doctorRef.isPresent()) {
            log.error("doctor exists for the mobile number {}", mobileNo);
            throw new DoctorAlreadyExistsException("Doctor already present for: "+ mobileNo);
        }
        DoctorEntity doctor = new DoctorEntity();
        doctor.setMobileNo(mobileNo);
        doctor.setHaveRegNo(request.getHaveRegNo());
        if(request.getHaveRegNo().equalsIgnoreCase("Y")) doctor.setRegNo(request.getRegNo());
        doctor.setDoctorInfo(request.getDoctorInfo());
        doctor.setEducationalDetails(request.getEducationalDetails());
        doctor.setExperienceDetails(request.getExperienceDetails());
        doctor.setUserName(request.getUserName());
        doctor.setPassword(request.getPassword());

        doctor.setCreatedBy(mobileNo);
        doctor.setCreatedOn(LocalDateTime.now());
        doctor.setLastUpdatedBy(mobileNo);
        doctor.setLastUpdatedOn(LocalDateTime.now());
        doctorRepo.save(doctor);
        return "Account Created";
    }

    public GetDoctorResponse getDoctorAccount(String mobileNo) {

        GetDoctorResponse response = new GetDoctorResponse();
        BeanUtils.copyProperties(getDoctor(mobileNo), response);
        return response;
    }

    public String updateDoctorAccount(String mobileNo, UpdateDoctorRequest request) {

        DoctorEntity doctor = getDoctor(mobileNo);
        doctor.setHaveRegNo(request.getHaveRegNo());
        if(request.getHaveRegNo().equalsIgnoreCase("Y")) doctor.setRegNo(request.getRegNo());
        doctor.setDoctorInfo(request.getDoctorInfo());
        doctor.setEducationalDetails(request.getEducationalDetails());
        doctor.setExperienceDetails(request.getExperienceDetails());

        doctor.setLastUpdatedBy(mobileNo);
        doctor.setLastUpdatedOn(LocalDateTime.now());
        doctorRepo.save(doctor);
        log.info("doctor account updated for: {}", mobileNo);
        return "Account Updated";
    }
}
