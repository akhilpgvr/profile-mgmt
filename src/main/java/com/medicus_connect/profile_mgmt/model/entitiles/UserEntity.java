package com.medicus_connect.profile_mgmt.model.entitiles;

import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class UserEntity {

    @Id
    @Generated
    private String id;

    private String userId;
    private String mobileNo;
    private PersonalInfo userInfo;

    private String userName;
    private String password;

    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime lastUpdatedOn;
    private String lastUpdatedBy;
}
