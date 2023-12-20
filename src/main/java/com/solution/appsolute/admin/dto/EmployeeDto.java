package com.solution.appsolute.admin.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//페이지처리
//정보 수정 시 사용
//가입시에도 사용해야함!!!!!(Register)
public class EmployeeDto {


    private Long empNum;

    private String empName;

    private Long deptNo;

    private String empPhone;

    private String empEmail;

    private String empPassword;

    private LocalDate empHireDate;

    private String empPosition;

    private int empLeader;

    private int empMgr;

    private double empAnnual;

    private String empHireDateFormatted;

    private LocalDateTime lastAnnualUpdateDate;

}
