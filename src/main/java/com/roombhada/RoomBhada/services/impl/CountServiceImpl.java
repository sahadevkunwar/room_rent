package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.Category;
import com.roombhada.RoomBhada.entities.RenterDetails;
import com.roombhada.RoomBhada.entities.Room;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.repositories.UserRepository;
import com.roombhada.RoomBhada.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RenterDetailsService renterDetailsService;

    @Override
    public Map<String, Long> countPostOfUser(Long userId) throws UserNotFoundException {
        Long  totalRooms = 0L;
        List<Room> roomList = this.roomService.getRoomsByUser(userId);
        totalRooms = roomList.stream().count();
        Map<String, Long> map= new HashMap<>();
         map.put("totalRooms",totalRooms);
        return map;
    }

    @Override
    public Map<String, Long> countVacantRoomOfUser(Long userId) throws UserNotFoundException {
        Long totalVacant = 0L;
        List<Room> roomList = this.roomService.getVacantRoomsOfUser(userId);
        totalVacant = roomList.stream().count();
        Map<String, Long> map = new HashMap<>();
        map.put("totalVacant",totalVacant);
        return map;
    }

    @Override
    public Map<String, Long> countOccupiedRoomOfUser(Long userId) throws UserNotFoundException {
        Long totalOccupied = 0L;
        List<Room> roomList = this.roomService.getOccupiedRoomsOfUser(userId);
        totalOccupied = roomList.stream().count();
        Map<String, Long> map = new HashMap<>();
        map.put("totalOccupied",totalOccupied);
        return map;
    }

    @Override
    public Map<String, Long> countTotalUsers() {
        Long totalUsers = 0L;
        List<User> userList = this.userService.getAllUsers();
        totalUsers = userList.stream().count();
        Map<String,Long> map = new HashMap<>();
        map.put("totalUsers",totalUsers);
        return map;
    }

    @Override
    public Map<String, Long> countTotalCategories() {
        Long totalCategories = 0L;
        Set<Category> categorySet = this.categoryService.getAllCategory();
        totalCategories = categorySet.stream().count();
        Map<String,Long> map = new HashMap<>();
        map.put("totalCategories",totalCategories);
        return map;
    }

    @Override
    public Map<String, Long> countTotalUsersByRole() {
        Long totalRenters= 0L;
        Long totalOwners = 0L;

        List<User> userList = this.userService.getUserByRole();
        totalRenters = userList.stream().count();

        List<User> userList1= this.userRepo.findAll();
        List<User> ownerList = userList1.stream().filter(user -> "owner".equals(user.getAccountType())).collect(Collectors.toList());
        totalOwners = ownerList.stream().count();

        Map<String,Long> map = new HashMap<>();
        map.put("totalRenters",totalRenters);
        map.put("totalOwners",totalOwners);
        return map;
    }

    @Override
    public Map<String, Long> countTotalRenterOfOwner() {
        Long totalRentersOfOwner = 0L;
        Set<RenterDetails> renterDetailsList= this.renterDetailsService.getAllRenterDetails();
        totalRentersOfOwner = renterDetailsList.stream().count();
        Map<String,Long> map = new HashMap<>();
        map.put("totalRentersOfOwner",totalRentersOfOwner);
        return map;
    }
}
