package com.medicus_connect.profile_mgmt.model.dtos.response;

import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

    public String mobileNo;
    public PersonalInfo userInfo;

    public String userName;
    public String password;
}
