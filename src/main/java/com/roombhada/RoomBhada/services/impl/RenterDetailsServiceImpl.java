package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.RenterDetails;
import com.roombhada.RoomBhada.entities.Room;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.exceptions.RoomNotFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.repositories.RenterDetailsRepository;
import com.roombhada.RoomBhada.repositories.RoomRepository;
import com.roombhada.RoomBhada.repositories.UserRepository;
import com.roombhada.RoomBhada.services.RenterDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class RenterDetailsServiceImpl implements RenterDetailsService {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RenterDetailsRepository renterDetailsRepo;

    @Override
    public RenterDetails addRenterDetails(RenterDetails renterDetails,Long roomId, String email) throws RoomNotFoundException {

        Room room = this.roomRepo.findById(roomId).orElseThrow(()->new RoomNotFoundException("Room not found"));
        renterDetails.setRoom(room);
        User user = this.userRepo.findByEmail(email);
        renterDetails.setUser(user);


        return this.renterDetailsRepo.save(renterDetails);
    }

    @Override
    public Set<RenterDetails> getAllRenterDetails() {
        return new LinkedHashSet<>(this.renterDetailsRepo.findAll());
    }

    @Override
    public RenterDetails getRenterDetailById(Long renterId) {
        return this.renterDetailsRepo.findById(renterId).get();
    }

    @Override
    public List<RenterDetails> getRenterDetailsByUser(Long userId) throws UserNotFoundException {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found !!"));
        List<RenterDetails> renterDetails = this.renterDetailsRepo.findByUser(user);
        return renterDetails;
    }
}
