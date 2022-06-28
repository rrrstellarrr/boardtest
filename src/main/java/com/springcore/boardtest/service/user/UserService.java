package com.springcore.boardtest.service.user;

import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.domain.user.UserRole;
import com.springcore.boardtest.dto.user.UserRequestDto_valid;
import com.springcore.boardtest.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // 회원가입
    public Long save(UserRequestDto_valid userRequestDto) {
        // vaidateUserName(userRequestDto);
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRequestDto.setRole(UserRole.USER);

        return userRepository.save(userRequestDto.toEntity()).getUserIdx();
    }

//    private void vaidateUserName(UserRequestDto_valid userRequestDto) {
//        userRepository.findByUsername(userRequestDto.getUsername()).ifPresent(user -> {
//            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
//        });
//    }

    // 회원가입 시, 아이디 중복 체크
    public boolean duplicateCheckUserName(String username) {
        log.info("Check username: {}", username);

         return userRepository.existsByUsername(username);
    }

    // 회원가입 시, 이메일 중복 체크
    public boolean duplicateCheckEmail(String email) {
        log.info("Check username: {}", email);

        return userRepository.existsByEmail(email);
    }

    // 회원가입 시, 닉네임 중복 체크
    public boolean duplicateCheckNickname(String nickname) {
        log.info("Check username: {}", nickname);

        return userRepository.existsByNickname(nickname);
    }

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // 회원정보 수정
    @Transactional
    public void update(UserRequestDto_valid userRequestDto, Long idx) {
        User findUser =  userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지않습니다. idx: " + idx));

        String password = passwordEncoder.encode(userRequestDto.getPassword());

        findUser.update(password, userRequestDto.getEmail(), userRequestDto.getNickname());
        log.info("### update param: {}", findUser);

         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(findUser.getUsername(), userRequestDto.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);
//        return findUser.getUserIdx();
    }

    // 회원탈퇴
    @Transactional
    public void delete(Long id) {
        User findUser =  userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지않습니다. id: " + id));
        userRepository.delete(findUser);
    }

}
