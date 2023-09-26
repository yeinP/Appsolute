package com.solution.appsolute.service.login;

import com.solution.appsolute.dto.login.LoginDto;
import com.solution.appsolute.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;


    public LoginDto selectByEmp_num(Long emp_num) {
           return loginMapper.selectByEmp_num(emp_num);
       }
}
