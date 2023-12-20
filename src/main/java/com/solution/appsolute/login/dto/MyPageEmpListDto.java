package com.solution.appsolute.login.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyPageEmpListDto {

    private Long emp_num;

    private String emp_name;

    private Long dept_no;

    private String emp_position;

    private String dept_name;

}
