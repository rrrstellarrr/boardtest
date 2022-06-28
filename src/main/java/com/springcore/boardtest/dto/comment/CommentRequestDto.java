package com.springcore.boardtest.dto.comment;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.comment.Comment;
import com.springcore.boardtest.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequestDto {

    private String content;
    private Board board;
    private User user;

    public Comment toEntity() {
        Comment comment = Comment.builder()
                .content(content)
                .board(board)
                .user(user)
                .build();
        return comment;
    }
}
