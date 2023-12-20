package com.solution.appsolute.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private int empNum;
    private int deptNo;
    private String empEmail;
    private String empHireDate;
    private int empLeader;
    private int empMgr;
    private String empName;
    private String empPassword;
    private String empPhone;
    private String empPosition;

}
