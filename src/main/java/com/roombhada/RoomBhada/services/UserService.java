package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.entities.ApiResponse;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.entities.UserRole;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by email
    public User getUser(String email);

    //delete by id
    public void deleteUserById(Long userId);

    //get all users
    public List<User> getAllUsers();

    //get all users by email and roles
    public List<User> getUserByRole();

    //update reset password Token
    public void updateResetPasswordToken(String email, String token) throws UserNotFoundException;

    //get user by resetPasswordToken
    public User getUserByResetPasswordToken(String resetPasswordToken);

    //update password while forget password
    public User updatePassword(User user, String newPassword);

    //change password
    public boolean changePassword(String oldPassword, String newPassword, String userName);

    //verify otp
    public boolean verifyOtp(String otp);

}
