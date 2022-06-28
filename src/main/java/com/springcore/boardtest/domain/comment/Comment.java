package com.springcore.boardtest.domain.comment;

import com.springcore.boardtest.domain.BaseTimeEntity;
import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long commentIdx;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardIdx")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

}
