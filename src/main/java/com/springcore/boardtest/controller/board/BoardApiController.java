package com.springcore.boardtest.controller.board;

import com.springcore.boardtest.dto.board.BoardRequestDto;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/write")
    public Long save(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal CustomUserDetails principalDetail) {
        return boardService.save(boardRequestDto, principalDetail);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        log.info("update params={}", boardRequestDto);
        boardService.update(id, boardRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        log.info("id :{}", id);
        boardService.delete(id);
        return ResponseEntity.ok(id);
    }

}
