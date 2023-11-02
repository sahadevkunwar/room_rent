package com.roombhada.RoomBhada.services;

import com.roombhada.RoomBhada.entities.Comment;
import com.roombhada.RoomBhada.exceptions.RoomNotFoundException;
import com.roombhada.RoomBhada.exceptions.UserNotFoundException;

import java.util.List;

public interface CommentService {

    //create comment
    Comment createComment(Comment comment, Long roomId, Long userId) throws RoomNotFoundException, UserNotFoundException;

    //update comment
    Comment updateComment(Comment comment, Long roomId, Long commentId);

    //delete comment
    void deleteComment(Long commentId) throws Exception;

}
