package com.springcore.boardtest.validator;

import com.springcore.boardtest.dto.user.UserRequestDto;
import com.springcore.boardtest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckUserValidator extends AbstractValidator<UserRequestDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) {
        if(userRepository.existsByUsername(dto.toEntity().getUsername())) {
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디입니다.");
        }

        if(userRepository.existsByEmail(dto.toEntity().getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일입니다.");
        }

        if(userRepository.existsByNickname(dto.toEntity().getNickname())) {
            errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임입니다.");
        }
    }
}
