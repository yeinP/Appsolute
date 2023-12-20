package com.solution.appsolute.board.dao;

import com.solution.appsolute.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    @Query("select c from BoardComment c where c.board.id = :id")
    List<BoardComment> findCommentsBoardId(@Param("id") Long id);


}
