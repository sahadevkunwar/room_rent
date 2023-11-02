package com.roombhada.RoomBhada.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment_tbl")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public Comment() {
    }

    public Comment(Long id, String content, Date createdDate, Room room, User user) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.room = room;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", room=" + room +
                ", user=" + user +
                '}';
    }

}
