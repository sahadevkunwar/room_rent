package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.entities.UserRole;
import com.roombhada.RoomBhada.exceptions.UserFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.repositories.RoleRepository;
import com.roombhada.RoomBhada.repositories.UserRepository;
import com.roombhada.RoomBhada.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

       User local =  this.userRepo.findByEmail(user.getEmail());

       if(local != null) {
           System.out.println("User with this email is already created !!");
           throw new UserFoundException();
       } else {

           //Create User
           for(UserRole ur:  userRoles) {
               roleRepo.save(ur.getRole());
           }

           user.getUserRoles().addAll(userRoles);
           local = this.userRepo.save(user);

       }

        return local;
    }

    //geting user by email
    @Override
    public User getUser(String email) {
        return this.userRepo.findByEmail(email);
    }

    //delete user by id
    @Override
    public void deleteUserById(Long userId) {
         this.userRepo.deleteById(userId);
    }

    //get all users
    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    //get user(renter) by role
    @Override
    public List<User> getUserByRole() {

        List<User> userList = this.userRepo.findAll();
        List<User> renterList= userList.stream().filter(user -> "renter".equals(user.getAccountType())).collect(Collectors.toList());
        return renterList;
    }

    //update reset password token
    @Override
    public void updateResetPasswordToken(String email, String token) throws UserNotFoundException {
        User user = this.userRepo.findByEmail(email);
        if(user!=null) {
                user.setResetPasswordToken(token);
                userRepo.save(user);
        }else {
            throw new UserNotFoundException("Could not find any user with email "+ email);
        }

    }

    //get user by reset password token
    @Override
    public User getUserByResetPasswordToken(String resetPasswordToken) {
        return userRepo.findByResetPasswordToken(resetPasswordToken);
    }

    //update password
    @Override
    public User updatePassword(User user, String newPassword) {
          System.out.println("User tes : "+user);
          System.out.println("New Password:"+newPassword);
           String encodedPassword = this.bCryptPasswordEncoder.encode(newPassword);
           user.setPassword(encodedPassword);
           user.setResetPasswordToken(null);

           userRepo.save(user);
           return  user;
    }

    //change password
    @Override
    public boolean changePassword(String oldPassword, String newPassword,String userName) {
        boolean flag = false;
        User currentUser = this.userRepo.findByEmail(userName);
        if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
            //change password
            currentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
            this.userRepo.save(currentUser);
            flag = true;

        } else {
            //error
             flag = false;
        }

        return flag;
    }

    @Override
    public boolean verifyOtp(String otp) {
        User user = this.getUserByResetPasswordToken(otp);
        if (user!=null){
            return true;
        }
        return false;
    }
}
