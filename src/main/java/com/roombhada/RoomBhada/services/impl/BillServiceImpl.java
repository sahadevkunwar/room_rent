package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.Bill;
import com.roombhada.RoomBhada.entities.RenterDetails;
import com.roombhada.RoomBhada.repositories.BillRepository;
import com.roombhada.RoomBhada.repositories.RenterDetailsRepository;
import com.roombhada.RoomBhada.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private RenterDetailsRepository renterDetailsRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill addBill(Bill bill, Long renterDetailsId) throws Exception {

        RenterDetails renterDetails = this.renterDetailsRepository.findById(renterDetailsId).orElseThrow(()-> new Exception("Renter Details not found !!"));
        bill.setRenterDetails(renterDetails);
        return this.billRepository.save(bill);
    }

    @Override
    public Set<Bill> getAllBill() {
        return new HashSet<>(this.billRepository.findAll());
    }

    @Override
    public Bill getBillById(Long billId) {
        return this.billRepository.findById(billId).get();
    }

    @Override
    public Set<Bill> getBillByRenterDetails(Long renterDetailsId) throws Exception {

        RenterDetails renterDetails = this.renterDetailsRepository.findById(renterDetailsId).orElseThrow(()-> new Exception("Renter Details not found !!"));
        Set<Bill> bills = this.billRepository.findByRenterDetails(renterDetails);
        return bills;
    }
}
