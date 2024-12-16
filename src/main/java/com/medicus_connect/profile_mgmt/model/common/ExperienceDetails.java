package com.medicus_connect.profile_mgmt.model.common;

import com.medicus_connect.profile_mgmt.model.enums.EmployeeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDetails {

    public String positionTitle;
    public EmployeeTypeEnum employeeType;
    public String organization;
    public String state;
    public String district;
    public String location;
    public String isCurrentlyWorking;
    public String startDate;
    public String endDate;



}
