package com.solution.appsolute.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long deptNo;

    @Column(length = 20, nullable = false)
    private String deptName;

    @Column(length = 30, nullable = false)
    private String deptLoc;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int deptBranch;

    public void changeDeptName(String deptName){
        this.deptName = deptName;
    }
    public void changeDeptLoc(String deptLoc){
        this.deptLoc = deptLoc;
    }

    public void changeDeptBranch(int deptBranch) {this.deptBranch = deptBranch;}
};