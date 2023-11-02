package com.roombhada.RoomBhada.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User with this email is not found. Try with another email...");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
