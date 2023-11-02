package com.roombhada.RoomBhada;

import com.roombhada.RoomBhada.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class RoomBhadaApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RoomBhadaApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code");

//      try {
//
//		  User user = new User();
//		  user.setFirstName("Shree");
//		  user.setLastName("Lohar");
//		  user.setEmail("shreejan1@gmail.com");
//		  user.setPassword(this.bCryptPasswordEncoder.encode("Shreejan"));
//		  user.setAddress("Kathmandu");
//		  user.setPhone("9837465755");
//		  user.setProfile("shree.jpg");
//		  user.setAddress("admin");
//
//		  Role role1 = new Role();
//		  role1.setRoleId(1L);
//		  role1.setRoleName("Admin");
//
//		  Set<UserRole> userRoleSet = new HashSet<>();
//		  UserRole userRole = new UserRole();
//		  userRole.setRole(role1);
//		  userRole.setUser(user);
//
//		  userRoleSet.add(userRole);
//
//		  User user1 = this.userService.createUser(user, userRoleSet);
//
//		  System.out.println(user1.getEmail());
//
//	  } catch (UserFoundException e) {
//		  e.printStackTrace();
//	  }

	}
}
