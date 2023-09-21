package com.solution.appsolute.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity

public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long deptNo;

    @Column(length = 20, nullable = false)
    private String DeptName;

    @Column(length = 30, nullable = false)
        private String DeptLoc;

    @Column(nullable = false)
    @ColumnDefault("0")
        private int DeptBranch;


};
