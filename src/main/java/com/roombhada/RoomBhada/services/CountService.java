package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.exceptions.UserNotFoundException;

import java.util.Map;

public interface CountService {

    Map<String, Long> countPostOfUser(Long userId) throws UserNotFoundException;

    Map<String, Long> countVacantRoomOfUser(Long userId) throws UserNotFoundException;

    Map<String, Long> countOccupiedRoomOfUser(Long userId) throws UserNotFoundException;

    Map<String,Long> countTotalUsers();

    Map<String, Long> countTotalCategories();

    Map<String, Long> countTotalUsersByRole();

    Map<String,Long> countTotalRenterOfOwner();
}
