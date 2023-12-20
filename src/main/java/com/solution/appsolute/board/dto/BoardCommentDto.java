package com.solution.appsolute.board.dto;

import com.solution.appsolute.entity.Employee;
import com.solution.appsolute.entity.Board;
import com.solution.appsolute.entity.BoardComment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardCommentDto {

    private Long id;
    private String content;
    private LocalDate createdDate;
    private String created_by;
    private Character delete_check;
    private Board board;
    private Employee employee;

    @Builder
    public BoardCommentDto(String content, LocalDate createdDate, String created_by, Character delete_check, Board board, Employee employee) {
        this.content = content;
        this.createdDate = createdDate;
        this.created_by = created_by;
        this.delete_check = delete_check;
        if(this.board != null){
            board.getBoardCommentList().remove(this);
        }else
            this.board = board;
        if(this.employee != null){
            employee.getBoardCommentList().remove(this);
        }else
            this.employee = employee;
    }

    public BoardComment toEntity() {
        return BoardComment.builder()
                .id(id)
                .content(content)
                .createdDate(createdDate)
                .created_by(created_by)
                .delete_check(delete_check)
                .employee(employee)
                .board(board)
                .build();

    }
}
