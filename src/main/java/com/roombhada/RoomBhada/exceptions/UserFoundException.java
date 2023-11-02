package com.roombhada.RoomBhada.exceptions;

public class UserFoundException extends Exception{

    public UserFoundException() {
        super("User with this email is already exists !! try with another email...");
    }

    public  UserFoundException(String msg) {
        super(msg);
    }

}
