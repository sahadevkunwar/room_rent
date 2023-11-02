package com.roombhada.RoomBhada.repositories;

import com.roombhada.RoomBhada.entities.Bill;
import com.roombhada.RoomBhada.entities.RenterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BillRepository extends JpaRepository<Bill, Long> {

    public Set<Bill> findByRenterDetails(RenterDetails renterDetails);
}
