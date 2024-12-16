package com.medicus_connect.profile_mgmt.model.dtos.Request;

import com.medicus_connect.profile_mgmt.model.common.EducationalDetails;
import com.medicus_connect.profile_mgmt.model.common.ExperienceDetails;
import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import com.medicus_connect.profile_mgmt.model.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDoctorRequest {

    public PersonalInfo doctorInfo;
    public List<EducationalDetails> educationalDetails;
    public List<ExperienceDetails> experienceDetails;

    public String userName;
    public String password;

    public LocalDateTime createdOn;
    public String createdBy;
    public LocalDateTime lastUpdatedOn;
    public String lastUpdatedBy;
}
