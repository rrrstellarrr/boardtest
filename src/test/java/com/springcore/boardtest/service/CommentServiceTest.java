package com.springcore.boardtest.service;

import com.springcore.boardtest.domain.comment.Comment;
import com.springcore.boardtest.domain.user.User;
import com.springcore.boardtest.dto.comment.CommentRequestDto;
import com.springcore.boardtest.repository.comment.CommentRepository;
import com.springcore.boardtest.repository.user.UserRepository;
import com.springcore.boardtest.service.comment.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void 댓글_작성() {

        // given
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setContent("우왕굿!!!");
        Long id = 1L;
        User testUser = userRepository.findById(id).get();

        // when
        Long saveId = commentService.save(id, commentRequestDto, testUser);

        // then
        Optional<Comment> result = commentRepository.findById(id);
        Assertions.assertThat(saveId).isEqualTo(result.get().getCommentIdx());
    }
}
