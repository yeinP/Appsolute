package com.solution.appsolute.login.mapper;


import com.solution.appsolute.login.dto.Calendar;
import com.solution.appsolute.login.dto.Employee;
import com.solution.appsolute.login.dto.MyPageEmpListDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LoginMapper {
    public Employee selectByEmp_num(Long emp_num);

    //2023.10.16
    public List<MyPageEmpListDto> myPageListEmp();


    //2023.10.18 사원 검색 기능 추가
    public List<MyPageEmpListDto> myPageListEmpByName(String name);

}