package com.springcore.boardtest.controller.board;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.dto.board.BoardResponseDto;
import com.springcore.boardtest.dto.comment.CommentResponseDto;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // index 화면
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, @PageableDefault(size = 5) Pageable pageable) {
        if(userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            //boardService.findTop5ByViewsOrderByViewsDesc()
        }

        return "index";
    }

    @GetMapping("/kkeujeok/list")
    public String searchList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(required = false, value = "category", defaultValue = "") String category,
                           @RequestParam(required = false, value = "search", defaultValue = "") String search) {
        if(userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        log.info("category: {}", category);
        log.info("search: {}", search);
        Page<Board> list = boardService.searchList(category, search, pageable);

        int startPage = (int) (Math.floor(list.getNumber() / 10) * 10 + 1);
        int endPage = ((startPage + 4) < list.getTotalPages()) ? startPage + 4 : list.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", list);
        return "/board/list";
    }

    @GetMapping("/kkeujeok/list{sortby}")
    public String list(Model model,
                       @PageableDefault(size = 10) Pageable pageable,
                       @PathVariable(required = false) String sortby) {
        log.info("sortby: {}", sortby);
        Page<Board> list = boardService.findAll(sortby, pageable);

        int startPage = (int) (Math.floor(list.getNumber() / 10) * 10 + 1);
        int endPage = ((startPage + 4) < list.getTotalPages()) ? startPage + 4 : list.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", list);

        return "/board/list";
    }

    @GetMapping("/kkeujeok/write")
    public String write() {
        return "/board/write";
    }

    @GetMapping("/kkeujeok/read/{id}")
    public String read(Model model, @PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        BoardResponseDto boardDto = boardService.findById(id);
        List<CommentResponseDto> comments = boardDto.getComments();

        // 조회수 올리기
        boardService.updateViews(id);

        if(userDetails.getUser() != null) {
            model.addAttribute("user", userDetails.getUser());
            if(boardDto.getUser().getUserIdx().equals(userDetails.getUser().getUserIdx())) {
                model.addAttribute("nickname", true);
            }
        }

        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("commentList", comments);
        }
        model.addAttribute("board", boardDto);
        return "/board/read";
    }

    @GetMapping("/kkeujeok/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("board", boardService.findById(id));
        return "/board/update";
    }

    @GetMapping("/mypage/list")
    public String list(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, @PageableDefault(size = 10) Pageable pageable) {
        Page<Board> list = boardService.findByUser(userDetails.getUser(), pageable);

        int startPage = (int) (Math.floor(list.getNumber() / 10) * 10 + 1);
        int endPage = ((startPage + 4) < list.getTotalPages()) ? startPage + 4 : list.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", list);

        return "/board/mypage_list";
    }

    @GetMapping("/contack")
    public String contack() {
        return "/board/contack";
    }

}
