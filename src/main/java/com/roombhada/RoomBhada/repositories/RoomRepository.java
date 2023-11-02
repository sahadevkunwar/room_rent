package com.roombhada.RoomBhada.repositories;

import com.roombhada.RoomBhada.entities.Category;
import com.roombhada.RoomBhada.entities.Room;
import com.roombhada.RoomBhada.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    public List<Room> findBycategory(Category category);

    public List<Room> findByEnabled(Boolean b);

    public List<Room> findByUsersAndStatus(User user,Boolean b);

    public List<Room> findByCategoryAndEnabled(Category c, boolean b);

    public List<Room> findByUsers(User user);

    public List<Room> findByUsersAndEnabled(User user,boolean b);

    @Query("select r from Room r where r.location like :key")
    public List<Room> findByLocation(@Param("key") String location);
}
