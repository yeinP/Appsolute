package com.solution.appsolute.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity

public class ApprovalLineDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long LineDetailNum;

    @Column( nullable = false)
    private int LineNum;

    @Column(nullable = false)
        private int EmpCheckNum;
    @Column(nullable = false)
           private int LineDetailSeq;

    @Column(nullable = false)
    private Long ApprovalLine;

    @Column(nullable = false)
    private Long Employee;


};
