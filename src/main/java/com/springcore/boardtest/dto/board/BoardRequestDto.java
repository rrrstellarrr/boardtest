package com.springcore.boardtest.dto.board;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.comment.Comment;
import com.springcore.boardtest.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardRequestDto {

    private String title;
    private String content;
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private int likes;
    private int views;

    public Board toEntity () {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .comments(comments)
                .likes(likes)
                .views(views)
                .build();
        return board;
    }
}
