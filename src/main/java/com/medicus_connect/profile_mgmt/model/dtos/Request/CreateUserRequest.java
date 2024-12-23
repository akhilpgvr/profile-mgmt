package com.medicus_connect.profile_mgmt.model.dtos.Request;

import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    public PersonalInfo userInfo;

    public String userName;
    public String password;
}
