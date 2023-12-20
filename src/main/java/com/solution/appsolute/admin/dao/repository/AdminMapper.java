package com.solution.appsolute.admin.dao.repository;

import com.solution.appsolute.admin.dto.EmpAnnualDto;
import com.solution.appsolute.admin.dto.EmployeeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    public List<EmpAnnualDto> listEmpAnnual(int startRow, int size);

    public Integer countAnnualList(int starRow, int size);

    public List<EmpAnnualDto> listAllEmployee(int startRow, int size);
    public Integer countListAllEmployee(int starRow, int size);

    public  EmpAnnualDto getEmployeeInfo(Long emp_num);

    public int modifyEmpAnnual(EmpAnnualDto empAnnualDto);


}
