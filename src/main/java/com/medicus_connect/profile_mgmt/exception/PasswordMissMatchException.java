package com.medicus_connect.profile_mgmt.exception;

public class PasswordMissMatchException extends RuntimeException {

    public PasswordMissMatchException(String message) {
        super(message);
    }
}
