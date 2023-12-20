package com.solution.appsolute.admin.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmpAnnualDto {

    private Long emp_num;

    private String emp_name;

    private Long dept_no;

    private String emp_phone;

    private String emp_email;

    private String emp_password;

    private LocalDateTime emp_hire_date;

    private String emp_position;

    private int emp_leader;

    private int emp_mgr;

    private double emp_annual;

    private String empHireDateFormatted;

}
