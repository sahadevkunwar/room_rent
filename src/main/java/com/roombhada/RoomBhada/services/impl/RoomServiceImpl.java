package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.Category;
import com.roombhada.RoomBhada.entities.Room;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.repositories.RoomRepository;
import com.roombhada.RoomBhada.repositories.UserRepository;
import com.roombhada.RoomBhada.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepo;

    //create room
    @Override
    public Room createRoom(Room room, int userId) throws UserNotFoundException {
        List<User> userList = new ArrayList<>();
        Long id = (long) userId;

        User user = this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException());
        userList.add(user);

        Room room1 = this.roomRepository.save(room);
        room1.setUsers(userList);
        return this.roomRepository.save(room1);
    }

    //update room
    @Override
    public Room updateRoom(Room room) {
        return this.roomRepository.save(room);
    }

    //get room by id
    @Override
    public Room getRoomById(Long roomId) {
        return this.roomRepository.findById(roomId).get();
    }

    //get all rooms
    @Override
    public Set<Room> getAllRooms() {
        return new HashSet<>(this.roomRepository.findAll());
    }

    //delete room by id
    @Override
    public void deleteRoomById(Long roomId) {
        this.roomRepository.deleteById(roomId);
    }

    //get room by category
    @Override
    public List<Room> getRoomsByCategory(Category category) {
        return this.roomRepository.findBycategory(category);
    }

    //get active rooms
    @Override
    public List<Room> getActiveRooms() {
        return this.roomRepository.findByEnabled(true);
    }

    //get active rooms by category
    @Override
    public List<Room> getActiveRoomByCategory(Category category) {
        return this.roomRepository.findByCategoryAndEnabled(category,true);
    }

    @Override
    public List<Room> searchRoom(String location) {
       return this.roomRepository.findByLocation("%"+location+"%");
    }

    @Override
    public List<Room> getRoomsByUser(Long userId) throws UserNotFoundException {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
        List<Room> rooms = this.roomRepository.findByUsers(user);

        return rooms;
    }

    @Override
    public List<Room> getActiveRoomsByUser(Long userId) throws UserNotFoundException {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        List<Room> rooms = this.roomRepository.findByUsersAndEnabled(user,true);
        return rooms;
    }

    @Override
    public List<Room> getVacantRoomsOfUser(Long userId) throws UserNotFoundException {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException());
        List<Room> rooms = this.roomRepository.findByUsersAndStatus(user,false);
        return rooms;
    }

    @Override
    public List<Room> getOccupiedRoomsOfUser(Long userId) throws UserNotFoundException {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
        List<Room> rooms = this.roomRepository.findByUsersAndStatus(user,true);
        return rooms;
    }

}
