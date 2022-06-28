package com.springcore.boardtest.service;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.dto.board.BoardRequestDto;
import com.springcore.boardtest.dto.board.BoardResponseDto;
import com.springcore.boardtest.repository.board.BoardRepository;
import com.springcore.boardtest.repository.user.UserRepository;
import com.springcore.boardtest.security.CustomUserDetails;
import com.springcore.boardtest.service.board.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void 글_등록() {

        // given
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("테스트글쓰기뿌뿌!!");
        boardRequestDto.setContent("테스트글쓰기 내용입니다.(짝짝쿵짝)");

        User testUser = userRepository.findById(1L).get();
        CustomUserDetails principalDetail = new CustomUserDetails(testUser);

        // when
        Long saveId = boardService.save(boardRequestDto, principalDetail);

        // then
        Optional<Board> result = boardRepository.findById(saveId);
        Assertions.assertThat(saveId).isEqualTo(result.get().getBoardIdx());
    }

    @Test
    void 글목록_조회() {
        // given
        List<Board> board = boardRepository.findAll();

        for(Board listAll : board) {
            System.out.println("title: " + listAll.getTitle());
            System.out.println("content: " + listAll.getContent());
            System.out.println("username: " + listAll.getUser().getUsername());
            System.out.println("nickname: " + listAll.getUser().getNickname());
        }
    }

    @Test
    void 글목록_페이징_검색() {
        // given

    }

    @Test
    void 글_읽기() {
        // given
        Long id = 10L;

        // when
        boardService.updateViews(id);
        BoardResponseDto board = boardService.findById(id);

        // then
        System.out.println(board.toString());
    }

    @Test
    void 글_수정() {
        // given
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("테스트글쓰기(수정)");
        boardRequestDto.setContent("테스트글쓰기 내용입니다.");

        // when
        boardService.update(1L, boardRequestDto);
    }

    @Test
    void 글_삭제() {
        // given
        Long id = 1L;

        // when
        boardService.delete(id);
    }


}
