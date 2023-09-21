package com.solution.appsolute.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class ApprovalLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long lineNum;

    @Column(nullable = false)
    private long empNum;

    @Column(length = 50, nullable = false)
    private long lineName;

    @Column(columnDefinition = "int default 0")
    private int lineDel;


}
