package com.springcore.boardtest.dto.user;

import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String username;
    private String password;
    private String email;
    private String nickname;
    private UserRole role;

    public User toEntity() {
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .role(role)
                .build();
        return user;
    }
}
