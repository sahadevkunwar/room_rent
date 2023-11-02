package com.roombhada.RoomBhada.exceptions;

public class RoomNotFoundException extends Exception{

    public RoomNotFoundException() {
        super("Post with this id is not found. Try with another id");
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
