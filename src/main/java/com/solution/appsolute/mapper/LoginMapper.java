package com.solution.appsolute.mapper;

import com.solution.appsolute.dto.login.LoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    public void insertEmp(LoginDto LoginDto);

    public LoginDto selectByEmp_num(Long emp_num);
}