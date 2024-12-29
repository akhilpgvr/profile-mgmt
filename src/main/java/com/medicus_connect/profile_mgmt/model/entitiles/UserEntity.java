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
    public String id;

    public String mobileNo;
    public PersonalInfo userInfo;

    public String userName;
    public String password;

    public LocalDateTime createdOn;
    public String createdBy;
    public LocalDateTime lastUpdatedOn;
    public String lastUpdatedBy;
}
