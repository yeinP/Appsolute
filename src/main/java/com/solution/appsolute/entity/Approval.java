package com.solution.appsolute.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long approvalNum;

    @Column(nullable = false)
    private long empNum;

    @Column(length = 1000, nullable = false)
    private String appContent;

    @Column(length = 500, nullable = false)
    private String appTitle;

    @Column(nullable = true, columnDefinition = "int default 0")
    private int appCheck;

    @Column
    private long lineNum;

    @Column
    private long documentNum;

    @Column(columnDefinition = "int default 0")
    private int appDel;




}
