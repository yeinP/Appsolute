package com.solution.appsolute.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PurchaseEmp {
    private long approvalNum;
    private long empNum;
    private String empPosition;
    private String empName;
    private long deptNo;
    private String deptName;

}
