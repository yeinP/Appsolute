package com.solution.appsolute.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "board_comment")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_comment_id")
    private Long id;
    private String content;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDate createdDate;

    private String created_by;

    private Character delete_check;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_num")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Employee employee;

    @Builder
    public BoardComment(String content, LocalDate createdDate, String created_by, Character delete_check, Board board, Employee employee) {
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
}
