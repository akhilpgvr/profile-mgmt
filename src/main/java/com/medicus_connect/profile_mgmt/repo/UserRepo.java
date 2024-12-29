package com.medicus_connect.profile_mgmt.repo;

import com.medicus_connect.profile_mgmt.model.entitiles.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByMobileNo(String mobileNo);

}
