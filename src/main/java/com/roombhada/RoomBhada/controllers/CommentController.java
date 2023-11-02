package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.entities.ApiResponse;
import com.roombhada.RoomBhada.entities.Comment;
import com.roombhada.RoomBhada.exceptions.RoomNotFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{roomId}/{userId}")
    public ResponseEntity<?> addComment(@RequestBody Comment comment,@PathVariable("roomId") Long roomId, @PathVariable("userId") Long userId) throws RoomNotFoundException, UserNotFoundException {
        Comment comment1 = this.commentService.createComment(comment,roomId,userId);
        return ResponseEntity.ok(comment1);
    }

    @DeleteMapping("/{commentId}")
    public  ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Long commentId) throws Exception {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>( new ApiResponse("Comment deleted successfully !!",true),HttpStatus.OK);
    }
}
