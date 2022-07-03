package com.springcore.boardtest.controller.user;

import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.dto.common.ResponseDto;
import com.springcore.boardtest.dto.user.UserUpdateDto;
import com.springcore.boardtest.service.user.UserService;
import com.springcore.boardtest.validator.CheckUserUpdateValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final CheckUserUpdateValidator checkUserUpdateValidator;

    // 유효성 체크
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUserUpdateValidator);
    }

    // 내 정보 수정요청 처리
    @PutMapping("/mypage/myinfo/update")
    public ResponseEntity<Map<String, String>> update(@RequestPart(value = "updateData") @Validated UserUpdateDto userRequestDto,
                         BindingResult bindingResult,
                         @RequestPart(value = "profileImage", required = false) MultipartFile multipartFile,
                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("화면에서 받아온 회원 정보: {}", userRequestDto);

        Map<String, String> resultMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            resultMap = userService.validateHandling(bindingResult);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        userService.update(userRequestDto, multipartFile, userDetails.getUser().getUserIdx());
        resultMap.put("success", "회원정보가 수정되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    // 회원탈퇴 처리
    @DeleteMapping("/mypage/myinfo/delete")
    public ResponseEntity<?> delete(@RequestBody String chkPassword, @AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("화면에서 받아온 chkPassword: {}", chkPassword);

        return userService.delete(userDetails.getUser().getUserIdx(), chkPassword) == "true" ? ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "회원탈퇴에 성공하였습니다.")) : ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "비밀번호를 확인해주세요."));
    }

}
