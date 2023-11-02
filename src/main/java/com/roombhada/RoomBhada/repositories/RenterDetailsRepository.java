package com.roombhada.RoomBhada.repositories;

import com.roombhada.RoomBhada.entities.RenterDetails;
import com.roombhada.RoomBhada.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RenterDetailsRepository extends JpaRepository<RenterDetails,Long> {

    public List<RenterDetails> findByUser(User user);
}
