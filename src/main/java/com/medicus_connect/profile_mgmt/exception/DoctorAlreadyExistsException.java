package com.medicus_connect.profile_mgmt.exception;

public class DoctorAlreadyExistsException extends RuntimeException{

    public DoctorAlreadyExistsException(String message) {
        super(message);
    }
}
