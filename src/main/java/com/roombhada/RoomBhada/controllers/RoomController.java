package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.entities.Category;
import com.roombhada.RoomBhada.entities.Room;
import com.roombhada.RoomBhada.exceptions.RoomNotFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.services.FileService;
import com.roombhada.RoomBhada.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    //create room
    @PostMapping("/{userId}")
    public ResponseEntity<?> createRoom(@RequestBody Room room, @PathVariable("userId") int userId) throws UserNotFoundException {
  try {
    	Room room2 = room;
    	System.out.println(room2);
        Room room1 = this.roomService.createRoom(room,userId);
        return ResponseEntity.ok(room1);
  } catch (NumberFormatException ex) {
	  ex.printStackTrace();
  } catch (MethodArgumentTypeMismatchException ex) {
	  ex.printStackTrace();
  }
return null;
    }

    //update room
    @PutMapping("/")
    public Room updateRoom(@RequestBody Room room) {
        return  this.roomService.updateRoom(room);
    }

    //get room by id
    @GetMapping("/{roomId}")
    public Room getById(@PathVariable("roomId") Long roomId) {
        return this.roomService.getRoomById(roomId);
    }

    //get all rooms
    @GetMapping("/")
    public ResponseEntity<?> getRooms() {
        return ResponseEntity.ok(this.roomService.getAllRooms());
    }

    //delete room by id
    @DeleteMapping("/{roomId}")
    public void deleteById(@PathVariable("roomId") Long roomId) {
        this.roomService.deleteRoomById(roomId);
    }


    //post image upload

    @PostMapping("/image/upload/{roomId}")
    public  ResponseEntity<Room> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Long roomId) throws IOException {

        Room room = this.roomService.getRoomById(roomId);
        String fileName = this.fileService.uploadImage(path,image);
        room.setRoomImage(fileName);
        Room updatedRoom = this.roomService.updateRoom(room);

        return new ResponseEntity<Room>(updatedRoom, HttpStatus.OK);
    }

   //method to get file
   @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

       InputStream resource = this.fileService.getResource(path,imageName);
       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
       StreamUtils.copy(resource,response.getOutputStream());
   }

   //method to get room by category
    @GetMapping("/category/{categoryId}")
    public List<Room> getRoomByCategory(@PathVariable("categoryId") Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return  this.roomService.getRoomsByCategory(category);
    }

   //get active rooms
   @GetMapping("/active")
   public List<Room> getActiveRooms() {
        return this.roomService.getActiveRooms();
   }

   //get active rooms by category
    @GetMapping("/category/active/{categoryId}")
    public List<Room> getActiveRoomsByCategory(@PathVariable("categoryId") Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return  this.roomService.getActiveRoomByCategory(category);
    }

    //search rooms
    @GetMapping("/search/{location}")
    public ResponseEntity<?> searchRoomsByLocation(@PathVariable("location") String location) {
        List<Room> result = this.roomService.searchRoom(location);
        return  new ResponseEntity<List<Room>>(result, HttpStatus.OK);
    }

    //get room by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRoomsByUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(this.roomService.getRoomsByUser(userId));
    }

    //get active rooms by user
    @GetMapping("/user/active/{userId}")
    public ResponseEntity<?> getActiveRoomsByUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return  ResponseEntity.ok(this.roomService.getActiveRoomsByUser(userId));
    }

    //get active vacant rooms of user
    @GetMapping("/user/vacant/{userId}")
    public ResponseEntity<?> getVacantRoomsByUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return  ResponseEntity.ok(this.roomService.getVacantRoomsOfUser(userId));
    }

    //get active vacant rooms of user
    @GetMapping("/user/occupied/{userId}")
    public ResponseEntity<?> getOccupiedRoomsByUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return  ResponseEntity.ok(this.roomService.getOccupiedRoomsOfUser(userId));
    }

    //exception handling for room
    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(RoomNotFoundException e) {
        return ResponseEntity.ok(e);
    }

}
