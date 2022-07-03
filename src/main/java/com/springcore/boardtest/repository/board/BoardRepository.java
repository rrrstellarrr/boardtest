package com.springcore.boardtest.repository.board;

import com.springcore.boardtest.domain.board.Board;
import com.springcore.boardtest.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 조회수 증가
    @Modifying
    @Query("UPDATE Board b SET b.views = b.views + 1 WHERE b.boardIdx = :id")
    int updateByViews(@Param("id") Long id);

    // 검색 & 페이징 (Containing를 붙여주면 Like검색이 가능을 함)
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    
    // Page<Board> findByContentContaining(String content, Pageable pageable);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    // 마이페이지 검색
    Page<Board> findByUser(User user, Pageable pageable);

    // 게시글 삭제 (회원 탈퇴)
    void deleteBoardByUser(User user);

}
