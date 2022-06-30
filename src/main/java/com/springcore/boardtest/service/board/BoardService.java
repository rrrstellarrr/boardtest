package com.springcore.boardtest.service.board;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.dto.board.BoardRequestDto;
import com.springcore.boardtest.dto.board.BoardResponseDto;
import com.springcore.boardtest.repository.board.BoardRepository;
import com.springcore.boardtest.config.auth.CustomUserDetails;
import com.springcore.boardtest.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public Long save(BoardRequestDto boardRequestDto, CustomUserDetails principalDetail) {
        boardRequestDto.setUser(principalDetail.getUser());
        return boardRepository.save(boardRequestDto.toEntity()).getBoardIdx();
    }

    // 글 목록(전체 ver.1)
    @Transactional
    public Page<Board> findAll(String sortby, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        String sortby_name = sortby.equals("") ? "boardIdx" : sortby;
        log.info("sortby_name: {}", sortby_name);
        pageable = PageRequest.of(page, pageable.getPageSize(), Sort.by(Sort.Direction.DESC, sortby_name));
        return boardRepository.findAll(pageable);
    }

    // 글 목록(검색 & 페이징)
    @Transactional
    public Page<Board> searchList(String category, String search, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        log.info("Category Name: {}", category);
        pageable = PageRequest.of(page, pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "boardIdx"));

        return (category.equals("title") ? boardRepository.findByTitleContaining(search, pageable) : boardRepository.findByTitleContainingOrContentContaining(search, search, pageable));
    }

    @Transactional
    public BoardResponseDto findById(Long id) {
        Board board = vaidateDuplicationBoardIdx(id);
        return new BoardResponseDto(board);
    }

    @Transactional
    public void update(Long id, BoardRequestDto boardRequestDto) {
        Board board = vaidateDuplicationBoardIdx(id);
        board.update(boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Board board = vaidateDuplicationBoardIdx(id);

        commentRepository.deleteCommentByBoard(board);
        boardRepository.deleteById(board.getBoardIdx());
    }

    // 조회수 증가 처리
    @Transactional
    public void updateViews(Long id) {
        boardRepository.updateByViews(id);
    }

    // 마이페이스 글 목록
    @Transactional
    public Page<Board> findByUser(User user, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "boardIdx"));
        return boardRepository.findByUser(user, pageable);
    }

    private Board vaidateDuplicationBoardIdx(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        return board;
    }

}
