package com.springcore.boardtest.controller.board;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.dto.board.BoardRequestDto;
import com.springcore.boardtest.security.CustomUserDetails;
import com.springcore.boardtest.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }

//    @GetMapping("/kkeujeok/search")
//    public void searchList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
//                           @PageableDefault(size = 10) Pageable pageable,
//                           @RequestParam(required = false, value = "category", defaultValue = "") String category,
//                           @RequestParam(required = false, value = "search") String search) {
//        if(userDetails != null) {
//            model.addAttribute("username", userDetails.getUsername());
//            //boardService.findTop5ByViewsOrderByViewsDesc()
//        }
//
//        log.info("category: {}", category);
//        log.info("search: {}", search);
//        Page<Board> list = boardService.searchList(category, search, pageable);
//
//        int startPage = (int) (Math.floor(list.getNumber() / 10) * 10 + 1);
//        int endPage = ((startPage + 4) < list.getTotalPages()) ? startPage + 4 : list.getTotalPages();
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("boardList", list);
//    }
}
