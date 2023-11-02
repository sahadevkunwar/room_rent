package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.entities.Category;
import com.roombhada.RoomBhada.entities.Room;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface RoomService {

    //create room
    Room createRoom(Room room, int userId) throws UserNotFoundException;

    //update room
    Room updateRoom(Room room);

    //get room by id
    Room getRoomById(Long roomId);

    //get all rooms
    Set<Room> getAllRooms();

    //delete room by id
    void deleteRoomById(Long roomId);

    //get room of category
    List<Room> getRoomsByCategory(Category category);

    //get active rooms
    List<Room> getActiveRooms();

    //get active rooms by category
    List<Room> getActiveRoomByCategory(Category category);

    //search room
    List<Room> searchRoom(String location);

    //get rooms by user
    List<Room> getRoomsByUser(Long userId) throws UserNotFoundException;

    //get active rooms by user
    List<Room> getActiveRoomsByUser(Long userId) throws UserNotFoundException;

    //get vacant rooms
    List<Room> getVacantRoomsOfUser(Long userId) throws UserNotFoundException;

    //get occupied rooms
    List<Room> getOccupiedRoomsOfUser(Long userId) throws UserNotFoundException;
}
