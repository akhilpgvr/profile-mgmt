package com.medicus_connect.profile_mgmt.model.entitiles;

import com.medicus_connect.profile_mgmt.model.common.EducationalDetails;
import com.medicus_connect.profile_mgmt.model.common.ExperienceDetails;
import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Doctor")
public class DoctorEntity {

    @Id
    @Generated
    private String id;

    private String doctorId;
    private String mobileNo;
    private String department; //one time value -- cannot be updated
    private String haveRegNo;
    private String regNo;
    private PersonalInfo doctorInfo;
    private List<EducationalDetails> educationalDetails;
    private List<ExperienceDetails> experienceDetails;

    private String userName;
    private String password;

    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime lastUpdatedOn;
    private String lastUpdatedBy;
}
