package com.springcore.boardtest.controller.comment;

import com.springcore.boardtest.dto.comment.CommentRequestDto;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.dto.common.ResponseDto;
import com.springcore.boardtest.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> save(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal CustomUserDetails principalDetail) {
        log.info("id :{}", id);
        log.info("화면에서 받아온 param :{}", commentRequestDto);
        commentService.save(id, commentRequestDto, principalDetail.getUser());
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "댓글이 등록되었습니다."));
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "댓글이 삭제되었습니다."));
    }

}
