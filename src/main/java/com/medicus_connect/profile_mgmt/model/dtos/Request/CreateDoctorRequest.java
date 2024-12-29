package com.medicus_connect.profile_mgmt.model.dtos.Request;

import com.medicus_connect.profile_mgmt.model.common.EducationalDetails;
import com.medicus_connect.profile_mgmt.model.common.ExperienceDetails;
import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDoctorRequest {

    private String mobileNo;
    public PersonalInfo doctorInfo;
    public List<EducationalDetails> educationalDetails;
    public List<ExperienceDetails> experienceDetails;

    public String userName;
    public String password;
}
