package com.solution.appsolute.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empNum;

    @Column(length=30, nullable = false)
    private String empName;

    @Column(nullable = false)
    private Long deptNo;

    @Column(length = 50, nullable = false)
    private String empPhone;

    @Column(length = 100, nullable = false)
    private String empEmail;

    @Column(length = 50, nullable = false)
    private String empPassword;

    @Column(nullable = true)
    private LocalDate empHireDate;

    @Column(length = 20, nullable = false)
    private String empPosition;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int empLeader;

    @Column
    private int empMgr;

    public void changeEmpName(String empName){
        this.empName = empName;
    }
    public void changeEmpPhone(String empPhone){
        this.empPhone = empPhone;
    }

    public void changeDeptNo(Long deptNo) {this.deptNo = deptNo;}

    public void changeEmpPosition(String empPosition) {this.empPosition = empPosition; }
    public void changeEmpLeader(int empLeader) {this.empLeader = empLeader;}

    public void changeEmpMgr(int empMgr) {this.empMgr = empMgr;}

//    @OneToMany(mappedBy = "employee") // 주인 필드 명
//    private List<Board> boardList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "employee")
//    private List<BoardComment> boardCommentList = new ArrayList<>();
//
//    @Builder
//    public Employee(String empName, Long deptNo, String empPhone, String empEmail, String empPassword, LocalDate empHireDate, String empPosition, int empLeader, int empMgr, List<Board> boardList, List<BoardComment> boardCommentList) {
//        this.empName = empName;
//        this.deptNo = deptNo;
//        this.empPhone = empPhone;
//        this.empEmail = empEmail;
//        this.empPassword = empPassword;
//        this.empHireDate = empHireDate;
//        this.empPosition = empPosition;
//        this.empLeader = empLeader;
//        this.empMgr = empMgr;
//        this.boardList = boardList;
//        this.boardCommentList = boardCommentList;
//    }
//
//    public Employee update(String empName){
//        this.empName = empName;
//        return this;
//    }
}
