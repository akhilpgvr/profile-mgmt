package com.medicus_connect.profile_mgmt.model.dtos.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocSlotRequest {

    @JsonIgnore
    private String mobileNo;
    private String date;
    private int startTime;
    private int endTime;
    private String location;
}
