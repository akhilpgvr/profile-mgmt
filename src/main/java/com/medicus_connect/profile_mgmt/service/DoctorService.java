package com.medicus_connect.profile_mgmt.service;

import com.medicus_connect.profile_mgmt.exception.DoctorAlreadyExistsException;
import com.medicus_connect.profile_mgmt.exception.DoctorNotExistsException;
import com.medicus_connect.profile_mgmt.exception.PasswordMissMatchException;
import com.medicus_connect.profile_mgmt.model.dtos.request.CreateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.request.DocSlotRequest;
import com.medicus_connect.profile_mgmt.model.dtos.request.UpdateDoctorRequest;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetDoctorResponse;
import com.medicus_connect.profile_mgmt.model.dtos.response.GetSlotsResponse;
import com.medicus_connect.profile_mgmt.model.entitiles.DoctorAvailEntity;
import com.medicus_connect.profile_mgmt.model.entitiles.DoctorEntity;
import com.medicus_connect.profile_mgmt.repo.DoctorAvailRepo;
import com.medicus_connect.profile_mgmt.repo.DoctorRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.management.QueryEval;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private ModelMapper modelMapper;

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

    public DoctorEntity getDoctorByDoctorId(String doctorId) {

        log.info("fetching doctor account for doctorId : {}", doctorId);
        Optional<DoctorEntity> doctorRef = doctorRepo.findByDoctorId(doctorId);
        if(doctorRef.isPresent()){
            log.info("doctor present for {}", doctorId);
            return doctorRef.get();
        }
        else {
            log.error("doctor not present for {}", doctorId);
            throw new DoctorNotExistsException("doctor not present for: "+ doctorId);
        }
    }

    public GetDoctorResponse getDoctorAccount(boolean isMobileNo, String mobileNo, String doctorId) {

        GetDoctorResponse response = new GetDoctorResponse();
        if(isMobileNo) BeanUtils.copyProperties(getDoctorByMobileNo(mobileNo), response);
        else BeanUtils.copyProperties(getDoctorByDoctorId(doctorId), response);
        return response;
    }

    public List<GetDoctorResponse> getDoctorList() {


        Query query = new Query();
        List<DoctorEntity> doctors = mongoTemplate.find(query, DoctorEntity.class);
        return doctors.stream().map(i-> modelMapper.map(i, GetDoctorResponse.class)).toList();
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

        String mobileNo = request.getMobileNo();
        log.info("checking doctor account using mobile no: {}", mobileNo);
        DoctorEntity doctor = getDoctorByMobileNo(mobileNo);
        DoctorAvailEntity docAvail = new DoctorAvailEntity();
        BeanUtils.copyProperties(request, docAvail);
        docAvail.setDoctorId(doctor.getDoctorId());
        docAvail.setDepartment(doctor.getDepartment());
        docAvail.setCreatedOn(LocalDateTime.now());
        docAvail.setCreatedBy(mobileNo);
        docAvail.setUpdatedOn(LocalDateTime.now());
        docAvail.setUpdatedBy(mobileNo);
        doctorAvailRepo.save(docAvail);
        log.info("new slot added for: {}", doctor.getDoctorId());
        return "New Slot Added";
    }


    public List<GetSlotsResponse> getSlotOfMonth(String mobileNo, int month) {

        // Get the current year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set to the first day of the given month
        calendar.set(Calendar.YEAR, currentYear); // Correctly set the year
        calendar.set(Calendar.MONTH, month); // Month is zero-based (0 = January)
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();

        // Set to the last day of the month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endDate = calendar.getTime();

        // Fetch doctor details
        DoctorEntity doctor = getDoctorByMobileNo(mobileNo);
        if (doctor == null || doctor.getDoctorId() == null) {
            throw new IllegalArgumentException("Doctor not found for the given mobile number.");
        }

        // Build the query
        Query query = new Query();
        query.addCriteria(Criteria.where("doctorId").is(doctor.getDoctorId()));
        query.addCriteria(Criteria.where("date").gte(startDate).lte(endDate));

        // Execute the query
        List<DoctorAvailEntity> docAvailList = mongoTemplate.find(query, DoctorAvailEntity.class);

        // Map and return the result
        return docAvailList.stream()
                .map(i -> modelMapper.map(i, GetSlotsResponse.class))
                .toList();
    }
}
