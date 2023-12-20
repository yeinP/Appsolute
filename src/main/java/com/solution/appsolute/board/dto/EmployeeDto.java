package com.solution.appsolute.board.dto;



import com.solution.appsolute.entity.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ApiModel
public class EmployeeDto {

    @ApiParam(value = "num", example = "1")
    private Long num;
    @ApiParam(value = "username", example = "u")
    private String empName;

    private Long deptNo;
    private String empPhone;
    private LocalDate empHireDate;
    private String empPosition;
    private int empLeader;
    private int empMgr;

    @ApiParam(value = "password", example = "p")
    private String empPassword;
    @ApiParam(value = "email", example = "e")
    private String empEmail;

    @Builder
    public EmployeeDto(String empName, Long deptNo, String empPhone, LocalDate empHireDate, String empPosition, int empLeader, int empMgr, String empPassword, String empEmail) {
        this.empName = empName;
        this.deptNo = deptNo;
        this.empPhone = empPhone;
        this.empHireDate = empHireDate;
        this.empPosition = empPosition;
        this.empLeader = empLeader;
        this.empMgr = empMgr;
        this.empPassword = empPassword;
        this.empEmail = empEmail;
    }

    public Employee toEntity(){
        return Employee.builder()
                .empName(empName)
                .deptNo(deptNo)
                .empPhone(empPhone)
                .empHireDate(empHireDate)
                .empPosition(empPosition)
                .empLeader(empLeader)
                .empMgr(empMgr)
                .empPassword(empPassword)
                .empEmail(empEmail)
                .build();
    }

}
