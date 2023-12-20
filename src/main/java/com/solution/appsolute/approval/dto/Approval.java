package com.solution.appsolute.approval.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Approval {

    private long approvalNum;
    private int appCheck;
    private Date approvalDate;
    private int appContent;
    private int appDel;
    private String appTitle;
    private int documentNum;
    private int empNum;
    private int lineNum;
    private Date regDate;
    private Date creationDate;

}
