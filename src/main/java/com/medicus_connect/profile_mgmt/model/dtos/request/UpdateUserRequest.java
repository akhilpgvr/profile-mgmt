package com.medicus_connect.profile_mgmt.model.dtos.request;

import com.medicus_connect.profile_mgmt.model.common.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private PersonalInfo userInfo;
}
