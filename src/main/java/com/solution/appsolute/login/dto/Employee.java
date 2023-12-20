package com.solution.appsolute.login.dto;


import com.solution.appsolute.login.exception.WrongIdPasswordException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Employee {
    private Long emp_num;
    private Long dept_no;
    private String emp_email;
    private Timestamp emp_hire_date;
    private int emp_leader;
    private Integer emp_mgr; // Integer로 선언하여 NULL 허용
    private String emp_name;
    private String emp_password;
    private String emp_phone;
    private String emp_position;
    public void changePassword(String oldPassword, String newPassword) {
        if (!emp_password.equals(oldPassword))
            throw new WrongIdPasswordException();
        this.emp_password = newPassword;
    }

    public boolean matchPassword(String password) {
        return this.emp_password.equals(password);
    }
}

