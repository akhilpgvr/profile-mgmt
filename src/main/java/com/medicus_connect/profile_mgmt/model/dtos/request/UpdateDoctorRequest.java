package com.medicus_connect.profile_mgmt.model.dtos.request;

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
public class UpdateDoctorRequest {

    public String haveRegNo;
    public String regNo;
    public PersonalInfo doctorInfo;
    public List<EducationalDetails> educationalDetails;
    public List<ExperienceDetails> experienceDetails;
}
