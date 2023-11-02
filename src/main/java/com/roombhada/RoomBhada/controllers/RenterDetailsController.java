package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.entities.RenterDetails;
import com.roombhada.RoomBhada.exceptions.RoomNotFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.services.RenterDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/renter/details")
@CrossOrigin("*")
public class RenterDetailsController {

    @Autowired
    private RenterDetailsService renterDetailsService;

    //add renters
    @PostMapping("/{roomId}/{email}")
    public ResponseEntity<?> addRenterDetails(@RequestBody RenterDetails renterDetails, @PathVariable("roomId") Long roomId, @PathVariable("email") String email) throws RoomNotFoundException {

        return ResponseEntity.ok(this.renterDetailsService.addRenterDetails(renterDetails,roomId,email));
    }

    //get all renters of owner
    @GetMapping("/")
    public ResponseEntity<?> getAllRenters() {

        return  ResponseEntity.ok(this.renterDetailsService.getAllRenterDetails());
    }

    @GetMapping("/{renterId}")
    public ResponseEntity<?> getRenterDetailsById(@PathVariable("renterId") Long renterId) {
        return  ResponseEntity.ok(this.renterDetailsService.getRenterDetailById(renterId));
    }

    //get renterDetails by user
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getRenterDetailsByUser(@PathVariable("userId") Long userId) throws UserNotFoundException {

        return  ResponseEntity.ok(this.renterDetailsService.getRenterDetailsByUser(userId));
    }
}
