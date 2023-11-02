package com.roombhada.RoomBhada.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="renter_details_tbl")
public class RenterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date rentDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Room room;

    @ManyToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "renterDetails",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Bill> bill = new LinkedHashSet<>();

    public RenterDetails() {
    }

    public RenterDetails(Long id, Date rentDate, Room room, User user, Set<Bill> bill) {
        this.id = id;
        this.rentDate = rentDate;
        this.room = room;
        this.user = user;
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Bill> getBill() {
        return bill;
    }

    public void setBill(Set<Bill> bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "RenterDetails{" +
                "id=" + id +
                ", rentDate=" + rentDate +
                ", room=" + room +
                ", user=" + user +
                ", bill=" + bill +
                '}';
    }
}
