package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.entities.RenterDetails;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.exceptions.RoomNotFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Set;

public interface RenterDetailsService {

    RenterDetails addRenterDetails(RenterDetails renterDetails,Long roomId, String email) throws RoomNotFoundException;

    Set<RenterDetails> getAllRenterDetails();

    //get RenterDetails by id
    RenterDetails getRenterDetailById(Long renterId);

    //get RenterDetails by renter or User
    List<RenterDetails> getRenterDetailsByUser(Long userId) throws UserNotFoundException;
}
