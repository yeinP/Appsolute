package com.solution.appsolute.login.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthInfo {
    private Long emp_num;
    private Long dept_no;
    private String emp_email;
    private int emp_leader;
    private Integer emp_mgr; // Integer로 선언하여 NULL 허용
    private String emp_name;
    private String emp_position;
    private String emp_password;
    private String emp_phone;


}
