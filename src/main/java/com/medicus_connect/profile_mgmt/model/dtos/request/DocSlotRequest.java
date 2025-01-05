package com.medicus_connect.profile_mgmt.model.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocSlotRequest {

    @JsonIgnore
    private String mobileNo;
    private Date date;
    private int startTime;
    private int endTime;
    private String location;
}
