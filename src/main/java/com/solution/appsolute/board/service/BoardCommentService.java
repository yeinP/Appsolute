package com.solution.appsolute.board.service;

import com.solution.appsolute.board.dao.BoardCommentRepository;
import com.solution.appsolute.board.dao.BoardRepository;
import com.solution.appsolute.board.dao.EmployeeRepository;
import com.solution.appsolute.board.dto.BoardCommentDto;
import com.solution.appsolute.entity.Board;
import com.solution.appsolute.entity.BoardComment;
import com.solution.appsolute.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class BoardCommentService {


    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;
    private EmployeeRepository employeeRepository;


    @Transactional
    public Long commentSave(String empName, Long id, BoardCommentDto dto) {

        Employee employee = employeeRepository.findByEmpName(empName);
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        dto.setEmployee(employee);
        dto.setBoard(board);

        BoardComment boardComment = dto.toEntity();
        boardCommentRepository.save(boardComment);

        return dto.getId();
    }

    @Transactional
    public void saveBoardComment(BoardCommentDto dto) {
//        BoardComment boardComment = dto.toEntity();
        BoardComment boardComment = BoardComment.builder()
                .content(dto.getContent())
                .created_by(dto.getCreated_by())
                .createdDate(LocalDate.now())
                .delete_check(dto.getDelete_check())
                .employee(dto.getEmployee())
                .board(dto.getBoard())
                .build();
        boardCommentRepository.save(boardComment);
    }

}
