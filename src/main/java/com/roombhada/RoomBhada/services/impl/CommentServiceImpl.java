package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.Comment;
import com.roombhada.RoomBhada.entities.Room;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.exceptions.RoomNotFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;
import com.roombhada.RoomBhada.repositories.CommentRepository;
import com.roombhada.RoomBhada.repositories.RoomRepository;
import com.roombhada.RoomBhada.repositories.UserRepository;
import com.roombhada.RoomBhada.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public Comment createComment(Comment comment, Long roomId, Long userId) throws RoomNotFoundException, UserNotFoundException {

        Room room = this.roomRepo.findById(roomId).orElseThrow(()->new RoomNotFoundException("Room not found"));
        comment.setRoom(room);

        User user = this.userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        comment.setUser(user);

        comment.setCreatedDate(new Date());

        return this.commentRepo.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment, Long roomId, Long commentId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) throws Exception {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow((() -> new Exception("Comment not found")));
        this.commentRepo.delete(comment);
    }
}
