package com.solution.appsolute.login.service;


import com.solution.appsolute.login.dto.Employee;
import com.solution.appsolute.login.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;


    public Employee selectByEmp_num(Long emp_num) {
           return loginMapper.selectByEmp_num(emp_num);
       }
}
