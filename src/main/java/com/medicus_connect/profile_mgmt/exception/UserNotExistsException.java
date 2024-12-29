package com.medicus_connect.profile_mgmt.exception;

public class UserNotExistsException extends RuntimeException{

    public UserNotExistsException(String message) {
        super(message);
    }
}
