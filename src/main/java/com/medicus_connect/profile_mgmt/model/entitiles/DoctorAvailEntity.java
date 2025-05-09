package com.medicus_connect.profile_mgmt.model.entitiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "doc-avail")
public class DoctorAvailEntity {

    @Id
    @Generated
    private String id;

    private String doctorId;
    private String department;
    private Date date;
    private int startTime;
    private int endTime;
    private String location;

    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;

}
