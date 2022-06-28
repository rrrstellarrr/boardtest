package com.springcore.boardtest.controller.user;

import com.springcore.boardtest.dto.user.UserRequestDto_valid;
import com.springcore.boardtest.security.CustomUserDetails;
import com.springcore.boardtest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    // 회원 가입 페이지
    @GetMapping("/join")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserRequestDto_valid());
        return "/user/join";
    }

    @PostMapping("/join")
    public String save(Model model, @Validated UserRequestDto_valid userRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            model.addAttribute("userDto", userRequestDto);

            Map<String, String> validateResult = userService.validateHandling(bindingResult);

            for (String key : validateResult.keySet()) {
                model.addAttribute(key, validateResult.get(key));
            }

            return "/user/join";
        }

        userService.save(userRequestDto);
        return "redirect:/";
    }

    // 마이페이지
    @GetMapping("/mypage/myinfo")
    public String mypage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", userDetails.getUser());
        return "/user/mypage";
    }

    // 내 정보 수정요청 처리
//    @PutMapping("/myinfo/update")
//    @ResponseBody
//    public ResponseEntity<?> update(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal CustomUserDetails userDetails) {
//        userService.update(userRequestDto, userDetails.getUser().getUserIdx());
//
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }

//    // 회원 탈퇴 요청 처리
//    @PostMapping("/delete/{id}")
//    public void delete(@PathVariable Long id) {
//        log.info("화면에서 받아온 탈퇴 회원 정보: " + id);
//        userService.delete(id);
//    }

    // 회원 탈퇴 요청 처리
    @DeleteMapping("/myinfo/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("화면에서 받아온 탈퇴 회원 정보: " + id);
        userService.delete(id);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
