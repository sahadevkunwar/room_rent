package com.roombhada.RoomBhada.repositories;

import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByResetPasswordToken(String Token);

}
