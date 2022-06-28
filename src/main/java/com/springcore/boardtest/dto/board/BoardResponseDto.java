package com.springcore.boardtest.dto.board;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.dto.comment.CommentResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long boardIdx;
    private String title;
    private String content;
    private User user;
    private int likes;
    private int views;
    private LocalDateTime createDate, modifiedDate;
    private List<CommentResponseDto> comments;

    public BoardResponseDto(Board board) {
        this.boardIdx = board.getBoardIdx();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.user = board.getUser();
        this.likes = board.getLikes();
        this.views = board.getViews();
        this.createDate = board.getCreateDate();
        this.modifiedDate = board.getModifiedDate();
        this.comments = board.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

}
