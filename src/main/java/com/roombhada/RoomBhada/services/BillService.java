package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.entities.Bill;

import java.util.Set;

public interface BillService {

    //create bill
    public Bill addBill(Bill bill, Long renterDetailsId) throws Exception;

    //get all bills
    public Set<Bill> getAllBill();

    //get bill by id
    public Bill getBillById(Long billId);

    //get bills by renterDetails
    public Set<Bill> getBillByRenterDetails(Long renterDetailsId) throws Exception;

}
