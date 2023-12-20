package com.solution.appsolute.board.dao;

import com.solution.appsolute.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAll();

    @Query("SELECT b FROM Board b WHERE b.countVisit = 0")
    List<Board> findTopByCountVisit();

    Page<Board> findByTitleContainingOrderByIdDesc(String title, Pageable pageable);

    void deleteById(Long id);
}
