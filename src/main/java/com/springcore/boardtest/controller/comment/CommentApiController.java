package com.springcore.boardtest.controller.comment;

import com.springcore.boardtest.dto.comment.CommentRequestDto;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public ResponseEntity save(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal CustomUserDetails principalDetail) {
        log.info("id :{}", id);
        log.info("화면에서 받아온 param :{}", commentRequestDto);
        return ResponseEntity.ok(commentService.save(id, commentRequestDto, principalDetail.getUser()));
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        log.info("id :{}", id);
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }

}
