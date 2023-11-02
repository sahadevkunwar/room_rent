package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.entities.*;
import com.roombhada.RoomBhada.exceptions.UserFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.services.EmailService;
import com.roombhada.RoomBhada.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    //Creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

        //encoding password with bcryptPasswordEncryptor
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();
        UserRole userRole = new UserRole();

        if(user.getAccountType().equals("owner")) {
            Role role = new Role();
            role.setRoleId(2L);
            role.setRoleName("Owner");

            userRole.setRole(role);
            userRole.setUser(user);
        } else if (user.getAccountType().equals("renter")) {
            Role role = new Role();
            role.setRoleId(3L);
            role.setRoleName("Renter");

            userRole.setRole(role);
            userRole.setUser(user);
        }

        roles.add(userRole);
        return this.userService.createUser(user,roles);
    }

    //Get user by email
    @GetMapping("/{email}")
    public User getUser(@PathVariable("email") String email) {

            return this.userService.getUser(email);
    }

    //Delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {

        this.userService.deleteUserById(userId);
    }

    //Get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
       return this.userService.getAllUsers();
    }

    //Get all users by role
    @GetMapping("/renter")
    public List<User> getAllUsersByRole() {
        return this.userService.getUserByRole();
    }

    //forget password
    @PostMapping("/verify")
    public ResponseEntity<?> forgetPassword(@RequestBody User user) {
             String token = RandomString.make(1);
             System.out.println(user);
             String email = user.getEmail();
             Boolean flag = false;
             try {
                 userService.updateResetPasswordToken(email,token);

                // String resetPasswordLink= Utility.getSiteURL()+"/reset_password?token="+token;

                 //send mail
                 String subject = "Mail for reset Password";
                 String message =""
                         +"<div style='border: 1px solid #e2e2e2; padding: 20px'>"
                         +"<h3>"+" Reset Link = "+"You have requested to reset your password."
                         +"Use this OTP to verify."+token+"</h3>"
                         +"</div>";
                 String to = email;
                 flag = this.emailService.sendEmail(subject,message,to);

             } catch (UserNotFoundException ex) {
                 ex.printStackTrace();
             }
           return ResponseEntity.ok(flag);
    }

    //verify otp
    @GetMapping("/verify/{otp}")
    public ResponseEntity<?> verifyOtp(@PathVariable("otp") String otp) {
        return ResponseEntity.ok(this.userService.verifyOtp(otp));
    }

    //change password while forget password
    @PostMapping("/change/password/{otp}")
    public ResponseEntity<?> changePassword(@RequestBody User user, @PathVariable("otp") String otp) {
        String newPassword = user.getPassword();
        User user1 = this.userService.getUserByResetPasswordToken(otp);
      return ResponseEntity.ok(this.userService.updatePassword(user1,newPassword));
    }

    //change password
    @PostMapping("/change/password/")
    public ResponseEntity<ApiResponse> changePasswordUser(@RequestBody ChangePassword changePassword, Principal principal) {
        String oldPassword = changePassword.getOldPassword();
        String newPassword = changePassword.getNewPassword();
        String userName = principal.getName();
        boolean response = this.userService.changePassword(oldPassword, newPassword, userName);
        if (response == true){
            return new ResponseEntity<ApiResponse>(new ApiResponse("Password changed successfully ...", true
            ), HttpStatus.OK);
    } else {
            return new ResponseEntity<ApiResponse>(new ApiResponse("Please enter correct old password , try again ...", false
            ), HttpStatus.OK);
        }
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException e) {
        return ResponseEntity.ok(e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserNotFoundException e) {
        return ResponseEntity.ok(e);
    }

}
