package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.entities.Bill;
import com.roombhada.RoomBhada.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/bill")
@CrossOrigin("*")
public class BillController {

    @Autowired
    private BillService billService;

    //create bill
    @PostMapping("/create/{renterDetailsId}")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable("renterDetailsId") Long renterDetailsId) throws Exception {

        return ResponseEntity.ok(this.billService.addBill(bill,renterDetailsId));
    }

    //get all bills
    @GetMapping("/list")
    public Set<Bill> getAllBill() {

        return this.billService.getAllBill();
    }

    //get bill by bill Id
    @GetMapping("/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable("billId") Long billId) {

        return  ResponseEntity.ok(this.billService.getBillById(billId));
    }

    //get all bills by renterDetails
    @GetMapping("/list/renter/{renterDetailsId}")
    public ResponseEntity<?> getBillByRenter(@PathVariable("renterDetailsId") Long renterDetailsId) throws Exception {

        return ResponseEntity.ok(this.billService.getBillByRenterDetails(renterDetailsId));
    }

}
