package com.medicus_connect.profile_mgmt.repo;

import com.medicus_connect.profile_mgmt.model.entitiles.DoctorAvailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorAvailRepo extends MongoRepository<DoctorAvailEntity, String> {

    Optional<DoctorAvailRepo> findByDoctorId(String doctorId);
}
