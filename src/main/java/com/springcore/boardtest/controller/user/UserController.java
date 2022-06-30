package com.springcore.boardtest.controller.user;

import com.springcore.boardtest.dto.user.UserRequestDto_valid;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.dto.user.UserUpdateDto_valid;
import com.springcore.boardtest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String save(@Validated UserRequestDto_valid userRequestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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
    @PutMapping("/myinfo/update")
    public String update(@Validated UserUpdateDto_valid updatetDto,
                         BindingResult bindingResult,
                         @RequestParam("profileImage") MultipartFile multipartFile,
                         @AuthenticationPrincipal CustomUserDetails userDetails,
                         RedirectAttributes redirectAttributes) {
        log.info("화면에서 받아온 회원 정보: {}", updatetDto);

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);

            Map<String, String> validateResult = userService.validateHandling(bindingResult);
            for (String key : validateResult.keySet()) {
                redirectAttributes.addFlashAttribute(key, validateResult.get(key));
            }
        } else {
            userService.update(updatetDto, multipartFile, userDetails.getUser().getUserIdx());
        }
        return "redirect:/mypage/myinfo";
    }

    // 회원 탈퇴페이지
    @GetMapping("/mypage/myinfo/delete")
    public String delete(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUser());
        return "/user/delete";
    }

    // 회원 탈퇴 요청 처리
    @DeleteMapping("/myinfo/delete")
    public String delete(@RequestParam("userIdx") Long id, @RequestParam("chkPassword") String check_Password, RedirectAttributes redirectAttributes) {

        boolean result = userService.checkPassword(id, check_Password);
        log.info("result: {}", result);
        if(result) {
            userService.delete(id);
        } else{
            redirectAttributes.addFlashAttribute("massage", "비밀번호를 확인해주세요.");
        }
        return "redirect:/mypage/myinfo/delete";
    }

}
