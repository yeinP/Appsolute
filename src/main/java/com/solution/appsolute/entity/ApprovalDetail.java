package com.solution.appsolute.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ApprovalDetail {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long ApprovalDetailNum;

    @Column
    private Long empCheckNum;

    @Column
    private Long approvalLineNum;

    @Column(columnDefinition = "int default 0")
    private int approvalStatus;

    @Column(nullable = false)
    private LocalDateTime approvalDate;

    @Column
    private String rejectReason;

    @Column(nullable = false)
    private int appDetailSeq;

    @Column(nullable = false)
    private Long approvalNum;


}
