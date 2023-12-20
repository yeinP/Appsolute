package com.solution.appsolute.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Basic {
    private long approvalNum;
    private String approvalDate;
    private String title;
    private int documentNum;
    private int empNum; //세션
    private int lineNum;
    private String regDate;
    private String creationDate;
}
