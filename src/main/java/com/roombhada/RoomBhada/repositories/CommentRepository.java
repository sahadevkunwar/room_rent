package com.roombhada.RoomBhada.repositories;

import com.roombhada.RoomBhada.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
