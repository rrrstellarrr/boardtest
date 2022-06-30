package com.springcore.boardtest.repository.comment;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.comment.Comment;
import com.springcore.boardtest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteCommentByBoard(Board board);

    void deleteCommentByUser(User user);
}
