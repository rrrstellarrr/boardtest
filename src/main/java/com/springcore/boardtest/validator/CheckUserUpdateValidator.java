package com.springcore.boardtest.validator;

import com.springcore.boardtest.dto.user.UserUpdateDto;
import com.springcore.boardtest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckUserUpdateValidator extends AbstractValidator<UserUpdateDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserUpdateDto dto, Errors errors) {
        if(!userRepository.findById(dto.getUserIdx()).get().getEmail().equals(dto.getEmail()) &&
                userRepository.existsByEmail(dto.toEntity().getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일입니다.");
        }

        if(!userRepository.findById(dto.getUserIdx()).get().getNickname().equals(dto.getNickname()) &&
                userRepository.existsByNickname(dto.toEntity().getNickname())) {
            errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임입니다.");
        }
    }
}
