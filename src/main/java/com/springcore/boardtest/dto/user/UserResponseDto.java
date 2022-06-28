package com.springcore.boardtest.dto.user;

import com.springcore.boardtest.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long userIdx;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private UserRole role;

}
