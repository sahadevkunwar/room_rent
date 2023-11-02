package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.services.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/count")
@CrossOrigin("*")
public class CountController {

    @Autowired
    private CountService countService;

    //count total rooms of user
    @GetMapping("/total/room/{userId}")
    public ResponseEntity<?> totalRoom(@PathVariable("userId") Long userId) throws UserNotFoundException {
      return ResponseEntity.ok(this.countService.countPostOfUser(userId));
    }

    //count total vacant rooms of user
    @GetMapping("/total/vacant/{userId}")
    public ResponseEntity<?> totalVacantRoom(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(this.countService.countVacantRoomOfUser(userId));
    }

    //count total occupied rooms of user
    @GetMapping("/total/occupied/{userId}")
    public ResponseEntity<?> totalOccupiedRoom(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(this.countService.countOccupiedRoomOfUser(userId));
    }

    //count total users
    @GetMapping("/total/users")
    public ResponseEntity<?> getTotalUser() {
        return ResponseEntity.ok(this.countService.countTotalUsers());
    }

    //count total categories
    @GetMapping("/total/categories")
    public ResponseEntity<?> getTotalCategories() {
        return ResponseEntity.ok(this.countService.countTotalCategories());
    }

    //count total users by role
    @GetMapping("/total/user/role")
    public ResponseEntity<?> getTotalUsersByRole() {
        return ResponseEntity.ok(this.countService.countTotalUsersByRole());
    }

    //count total renters of owner
    @GetMapping("/total/renter/of/owner")
    public ResponseEntity<?> getTotalRenterOfOwner() {
        return ResponseEntity.ok(this.countService.countTotalRenterOfOwner());
    }

}
