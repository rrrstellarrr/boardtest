package com.springcore.boardtest.controller.user;

import com.springcore.boardtest.dto.user.UserRequestDto;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.service.user.UserService;
import com.springcore.boardtest.validator.CheckUserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CheckUserValidator checkUsernameValidator;

    // 유효성 체크
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
    }

    // 회원 로그인 페이지
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/login";
    }

    // 회원 가입 페이지
    @GetMapping("/join")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserRequestDto());
        return "/user/join";
    }

    // 회원 가입 처리
    @PostMapping("/join")
    public String save(@Validated UserRequestDto userRequestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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

    // 회원 탈퇴페이지
    @GetMapping("/mypage/myinfo/delete")
    public String delete(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUser());
        return "/user/delete";
    }

}
