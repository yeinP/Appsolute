package com.solution.appsolute.login.service;


import com.solution.appsolute.login.dto.AuthInfo;
import com.solution.appsolute.login.dto.Employee;
import com.solution.appsolute.login.mapper.LoginMapper;
import com.solution.appsolute.login.exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private LoginMapper loginMapper;

    public AuthInfo authenticate(Long emp_num, String emp_password) {
        Employee employee = loginMapper.selectByEmp_num(emp_num);

        if (employee == null) {
            throw new WrongIdPasswordException();
        }
        if (!employee.matchPassword(emp_password)) {
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(employee.getEmp_num(),employee.getDept_no(),employee.getEmp_email(),employee.getEmp_leader()
        ,employee.getEmp_mgr(),employee.getEmp_name(),employee.getEmp_position(),employee.getEmp_password(),employee.getEmp_phone());
    }
}
