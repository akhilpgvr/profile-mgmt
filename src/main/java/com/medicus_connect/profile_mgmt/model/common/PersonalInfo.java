package com.medicus_connect.profile_mgmt.model.common;

import com.medicus_connect.profile_mgmt.model.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    public String name;
    public String dob;
    public Integer age;
    public GenderEnum gender;

    public Integer mobileNo;
    public String email;
}
