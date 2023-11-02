package com.roombhada.RoomBhada.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="room_tbl")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String roomTitle;
    private String phone;
    private String location;
    private Double price;
    private String description;
    private String facility;
    private String rules;
    private String roomImage;
    private boolean status = false;
    private boolean enabled = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comment = new LinkedHashSet<>();

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private RenterDetails renter;

    public Room() {
    }

    public Room(int id, String roomTitle, String phone, String location, Double price, String description, String facility, String rules, String roomImage, boolean status, boolean enabled, Category category, List<User> users, Set<Comment> comment, RenterDetails renter) {
        this.id = id;
        this.roomTitle = roomTitle;
        this.phone = phone;
        this.location = location;
        this.price = price;
        this.description = description;
        this.facility = facility;
        this.rules = rules;
        this.roomImage = roomImage;
        this.status = status;
        this.enabled = enabled;
        this.category = category;
        this.users = users;
        this.comment = comment;
        this.renter = renter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String floor) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public RenterDetails getRenter() {
        return renter;
    }

    public void setRenter(RenterDetails renter) {
        this.renter = renter;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomTitle='" + roomTitle + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", facility='" + facility + '\'' +
                ", rules='" + rules + '\'' +
                ", roomImage='" + roomImage + '\'' +
                ", status=" + status +
                ", enabled=" + enabled +
                ", category=" + category +
                ", users=" + users +
                ", comment=" + comment +
                ", renter=" + renter +
                '}';
    }
}
