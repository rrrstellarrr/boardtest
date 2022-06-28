package com.springcore.boardtest.dto.user;

import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto_valid {

    private Long userIdx;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-z]{1}(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,10}$", message = "아이디는 5~10자 영문 소문자, 숫자를 사용해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z]{1}(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,12}", message = "비밀번호는 6~12자 영문 대 소문자, 숫자를 사용해주세요.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "(?=.*[ㄱ-ㅎ가-힣])[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리입니다.")
    private String nickname;

    private UserRole role;

    public User toEntity() {
        User user = User.builder()
                .userIdx(userIdx)
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .role(role)
                .build();
        return user;
    }


}
