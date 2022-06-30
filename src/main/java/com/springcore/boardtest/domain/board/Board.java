package com.springcore.boardtest.domain.board;

import com.springcore.boardtest.domain.BaseTimeEntity;
import com.springcore.boardtest.domain.comment.Comment;
import com.springcore.boardtest.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long boardIdx;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userIdx")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "integer(5) default 0")
    private int likes;

    @Column(nullable = false, columnDefinition = "integer(5) default 0")
    private int views;

    // 게시글 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
