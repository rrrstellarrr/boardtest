package com.springcore.boardtest.service;

import com.springcore.boardtest.domain.user.UserRole;
import com.springcore.boardtest.dto.user.UserRequestDto;
import com.springcore.boardtest.repository.user.UserRepository;
import com.springcore.boardtest.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void 회원_가입() {

        // given
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername("user");
        requestDto.setPassword("111111");
        requestDto.setEmail("user@gmail.com");
        requestDto.setNickname("테스트유저");
        requestDto.setRole(UserRole.USER);

        // when
        Long saveId = userService.save(requestDto);

        // then
        Long result = userRepository.findByUsername("testuser2").get().getUserIdx();
        Assertions.assertThat(saveId).isEqualTo(result);
    }

    @Test
    void 회원정보_수정() {
        // given
        UserRequestDto requestDto = new UserRequestDto();
        Long testId = 2L;
        requestDto.setPassword("111111");
        requestDto.setEmail("user2@gmail.com");
        requestDto.setNickname("테스트유저이");
        MultipartFile multipartFile;

        // when
        // userService.update(requestDto, testId);

        // then
        // Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void 회원_탈퇴() {
        // given
        Long testId = 2L;
        String testPass = "111111";

        // when
        userService.delete(testId, testPass);

        // then
        boolean result = userRepository.findById(testId).isEmpty();
        Assertions.assertThat(result).isEqualTo(true);
    }
}
