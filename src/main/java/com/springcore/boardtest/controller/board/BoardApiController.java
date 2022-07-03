package com.springcore.boardtest.controller.board;

import com.springcore.boardtest.dto.board.BoardRequestDto;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.dto.common.ResponseDto;
import com.springcore.boardtest.service.board.BoardService;
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
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/kkeujeok/write")
    public ResponseEntity<?> save(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal CustomUserDetails principalDetail) {
        boardService.save(boardRequestDto, principalDetail);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "게시글이 등록되었습니다."));
    }

    @PutMapping("/kkeujeok/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        log.info("update params={}", boardRequestDto);
        boardService.update(id, boardRequestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "게시글이 수정되었습니다."));
    }

    @DeleteMapping("/kkeujeok/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "게시글이 삭제되었습니다."));
    }

}
