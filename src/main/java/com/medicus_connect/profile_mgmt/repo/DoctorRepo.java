package com.medicus_connect.profile_mgmt.repo;

import com.medicus_connect.profile_mgmt.model.entitiles.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends MongoRepository<DoctorEntity, String> {
}
