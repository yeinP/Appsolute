package com.solution.appsolute.dto.admin;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//페이지처리
//정보 수정 시 사용
public class EmployeeRequest {

    private Long empNum;

    private String empName;

    private Long deptNo;

    private String empPhone;

    private String empEmail;

    private String empPassword;

    private LocalDateTime empHireDate;

    private String empPosition;

    private int empLeader;

    private int empMgr;

}
