package com.springcore.boardtest.dto.comment;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.comment.Comment;
import com.springcore.boardtest.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long commentIdx;
    private String content;
    private Board board;
    private User user;
    private LocalDateTime createDate, modifiedDate;

    public CommentResponseDto(Comment comment) {
        this.commentIdx = comment.getCommentIdx();
        this.content = comment.getContent();
        this.board = comment.getBoard();
        this.user = comment.getUser();
        this.createDate = comment.getCreateDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
