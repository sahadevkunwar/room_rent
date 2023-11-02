package com.roombhada.RoomBhada.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill_tbl")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date rentFrom;

    private Date rentTo;

    private Double roomRent;

    private Double drinkingWater;

    private Double electricity;

    private Double roomWaste;

    private Double internet;

    private Double otherFacility;

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private RenterDetails renterDetails;

    public Bill() {
    }

    public Bill(Long id, Date rentFrom, Date rentTo, Double roomRent, Double drinkingWater, Double electricity, Double roomWaste, Double internet, Double otherFacility, Boolean status, RenterDetails renterDetails) {
        this.id = id;
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
        this.roomRent = roomRent;
        this.drinkingWater = drinkingWater;
        this.electricity = electricity;
        this.roomWaste = roomWaste;
        this.internet = internet;
        this.otherFacility = otherFacility;
        this.status = status;
        this.renterDetails = renterDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(Double roomRent) {
        this.roomRent = roomRent;
    }

    public Double getDrinkingWater() {
        return drinkingWater;
    }

    public void setDrinkingWater(Double drinkingWater) {
        this.drinkingWater = drinkingWater;
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public Double getRoomWaste() {
        return roomWaste;
    }

    public void setRoomWaste(Double roomWaste) {
        this.roomWaste = roomWaste;
    }

    public Double getInternet() {
        return internet;
    }

    public void setInternet(Double internet) {
        this.internet = internet;
    }

    public Double getOtherFacility() {
        return otherFacility;
    }

    public void setOtherFacility(Double otherFacility) {
        this.otherFacility = otherFacility;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public RenterDetails getRenterDetails() {
        return renterDetails;
    }

    public void setRenterDetails(RenterDetails renterDetails) {
        this.renterDetails = renterDetails;
    }

    public Date getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(Date rentFrom) {
        this.rentFrom = rentFrom;
    }

    public Date getRentTo() {
        return rentTo;
    }

    public void setRentTo(Date rentTo) {
        this.rentTo = rentTo;
    }

    public Double getTotalBill() {
        return  roomRent+drinkingWater+electricity+internet+roomWaste+otherFacility;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", rentFrom=" + rentFrom +
                ", rentTo=" + rentTo +
                ", roomRent=" + roomRent +
                ", drinkingWater=" + drinkingWater +
                ", electricity=" + electricity +
                ", roomWaste=" + roomWaste +
                ", internet=" + internet +
                ", otherFacility=" + otherFacility +
                ", status=" + status +
                ", renterDetails=" + renterDetails +
                '}';
    }
}
