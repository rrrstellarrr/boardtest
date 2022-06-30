package com.springcore.boardtest.domain.user;

import com.springcore.boardtest.domain.BaseTimeEntity;
import com.springcore.boardtest.domain.board.Board;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userIdx;

    // 아이디
    @Column(length = 30, unique = true, nullable = false)
    private String username;

    // 비밀번호
    @Column(length = 100, nullable = false)
    private String password;

    // 이메일
    @Column(length = 50, unique = true, nullable = false)
    private String email;

    // 닉네임
    @Column(length = 30, unique = true)
    private String nickname;

    // 권한
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    // 회원탈퇴 => 작성한 게시물 삭제
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    private String profileImage;

    // 회원정보 수정
    public void update(String password, String email, String nickname) {
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    // 회원 프로필 사진 수정
    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
