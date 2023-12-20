package com.solution.appsolute.approval.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApprovalDetail {
    private long approvalDetailNum;
    private long empCheckNum;
    private int appDetailSeq;
    private Date checkDate;
    private long approvalLineNum;
    private long approvalNum;
    private int approvalStatus;
    private String rejectReason;
}
