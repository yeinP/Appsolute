package com.solution.appsolute.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private Long countVisit;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_num")
    private Employee employee;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<BoardComment> boardCommentList = new ArrayList<>();



    public void setMember(Employee employee) {
        this.employee = employee;
        employee.getBoardList().add(this);
    }

    @Builder
    public Board(String title, String content, String createdBy, LocalDate createdDate, LocalDate modifiedDate,
                 Long countVisit, Employee employee, List<BoardComment> boardCommentList) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.countVisit = countVisit;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        if (this.employee != null) {
            employee.getBoardList().remove(this);
        }
        this.boardCommentList = boardCommentList;

    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDate.now();
    }


    public void updateVisit(Long countVisit) {
        this.countVisit = countVisit;
    }
}
