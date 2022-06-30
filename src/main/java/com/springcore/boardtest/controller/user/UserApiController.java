package com.springcore.boardtest.controller.user;

import com.springcore.boardtest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    // 아이디 중복 체크
    @PostMapping("/check/username")
    public ResponseEntity<String> checkUserName(@RequestParam("username") String username) {
        log.info("화면에서 받아온 username: {}", username);
        boolean chkResult = userService.duplicateCheckUserName(username);
        log.info("Check Result: {}", chkResult);

        return (chkResult == false) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.OK);
    }

    // 이메일 중복 체크
    @PostMapping("/check/email")
    public ResponseEntity<String> checkEmail(@RequestParam("email") String email) {
        log.info("화면에서 받아온 email: {}", email);
        boolean chkResult = userService.duplicateCheckEmail(email);
        log.info("Check Result: {}", chkResult);

        return (chkResult == false) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.OK);
    }

    // 닉네임 중복 체크
    @PostMapping("/check/nickname")
    public ResponseEntity<String> checkNickname(@RequestParam("nickname") String nickname) {
        log.info("화면에서 받아온 nickname: {}", nickname);
        boolean chkResult = userService.duplicateCheckNickname(nickname);
        log.info("Check Result: {}", chkResult);

        return (chkResult == false) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.OK);
    }

}
