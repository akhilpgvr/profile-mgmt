package com.medicus_connect.profile_mgmt.model.dtos.Request;

import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String mobileNo;
    private PersonalInfo userInfo;

    private String userName;
    private String password;
}
