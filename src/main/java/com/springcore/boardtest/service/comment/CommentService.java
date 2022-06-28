package com.springcore.boardtest.service.comment;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.dto.comment.CommentRequestDto;
import com.springcore.boardtest.repository.board.BoardRepository;
import com.springcore.boardtest.repository.comment.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Long id, CommentRequestDto commentRequestDto, User user) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        commentRequestDto.setBoard(board);
        commentRequestDto.setUser(user);
        commentRepository.save(commentRequestDto.toEntity());
        return commentRequestDto.getBoard().getBoardIdx();
    }
}
