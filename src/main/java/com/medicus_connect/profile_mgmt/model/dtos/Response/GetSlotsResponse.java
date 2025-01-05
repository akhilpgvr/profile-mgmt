package com.medicus_connect.profile_mgmt.model.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSlotsResponse {

    private String doctorId;
    private Date date;
    private int startTime;
    private int endTime;
    private String location;
}
