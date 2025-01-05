package com.medicus_connect.profile_mgmt.service;

import com.medicus_connect.profile_mgmt.exception.DoctorAlreadyExistsException;
import com.medicus_connect.profile_mgmt.exception.DoctorNotExistsException;
import com.medicus_connect.profile_mgmt.exception.PasswordMissMatchException;
import com.medicus_connect.profile_mgmt.model.dtos.Request.CreateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Request.DocSlotRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Request.UpdateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.Response.GetDoctorResponse;
import com.medicus_connect.profile_mgmt.model.entitiles.DoctorAvailEntity;
import com.medicus_connect.profile_mgmt.model.entitiles.DoctorEntity;
import com.medicus_connect.profile_mgmt.repo.DoctorAvailRepo;
import com.medicus_connect.profile_mgmt.repo.DoctorRepo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.medicus_connect.profile_mgmt.Helper.generateDocId;

@Slf4j
@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DoctorAvailRepo doctorAvailRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    //------------------------------------Profile Services-----------------------------------

    public DoctorEntity getDoctorByMobileNo(String mobileNo) {

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
        doctor.setDoctorId(generateDocId(mobileNo));
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
        BeanUtils.copyProperties(getDoctorByMobileNo(mobileNo), response);
        return response;
    }

    public String updateDoctorAccount(String mobileNo, UpdateDoctorRequest request) {

        DoctorEntity doctor = getDoctorByMobileNo(mobileNo);
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



    //------------------------------------Slot Services-----------------------------------

    public String addDocSlot(DocSlotRequest request) {

        log.info("checking doctor account using mobile no: {}", request.getMobileNo());
        DoctorEntity doctor = getDoctorByMobileNo(request.getMobileNo());
        DoctorAvailEntity docAvail = new DoctorAvailEntity();
        BeanUtils.copyProperties(request, docAvail);
        docAvail.setDoctorId(doctor.getDoctorId());
        doctorAvailRepo.save(docAvail);
        log.info("new slot added for: {}", doctor.getDoctorId());
        return "New Slot Added";
    }


    public String getSlotOfMonth(String mobileNo, String month) {

        DoctorEntity doctor = getDoctorByMobileNo(mobileNo);
        Query query = new Query();
        query.addCriteria(Criteria.where("").is(mobileNo));
//        query.addCriteria(Criteria.where("").lte(new Data[]).gte());
        List<DoctorAvailEntity> docAvailList = mongoTemplate.find(query, DoctorAvailEntity.class);

        return "";
    }
}
