package com.solution.appsolute.admin.service;

import com.solution.appsolute.admin.dto.*;
import com.solution.appsolute.entity.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface AdminEmployeeService {
    //EMPLOYEE
    //Emp 등록
    Long registerEmployee(EmployeeDto employeeDto);




    EmployeeDto readEmployee(Long empNum);

    void removeEmployee(Long empNum);

    void modifyEmployee(EmployeeDto employeeDto);

    void modifyEmpAnnual(EmployeeDto employeeDto);

    //emp페이지
    PageResultDto<EmployeeDto, Employee> getEmployeeList(PageRequestDto requestDto);

   AnnualPage getEmpAnnualList(int pageNum);

    AnnualPage getAllEmpAnnualList(int pageNum);

    void updateEmpAnnualByEmpNums(List<Long> empNums);


    void modifyEmployeeDept(EmployeeDto employeeDto);

    PageResultDto<EmployeeDto, Employee> getEmployeeListByDeptNo(PageRequestDto requestDto, long deptNo);

    default Employee employeeDtoToEntity(EmployeeDto employeeDto) {
        Employee entity = Employee.builder().empNum(employeeDto.getEmpNum()).empName(employeeDto.getEmpName()).empEmail(employeeDto.getEmpEmail())
                .empPhone(employeeDto.getEmpPhone()).empMgr(employeeDto.getEmpMgr()).empPassword(employeeDto.getEmpPassword()).empPosition(employeeDto.getEmpPosition())
                .empHireDate(LocalDate.now()).empLeader(employeeDto.getEmpLeader()).deptNo(employeeDto.getDeptNo()).empAnnual(employeeDto.getEmpAnnual()).lastAnnualUpdateDate(employeeDto.getLastAnnualUpdateDate()).build();
        return entity;
    }

    default EmployeeDto entityToemployeeDto(Employee employee) {
        EmployeeDto employeeDto  = EmployeeDto.builder().empNum(employee.getEmpNum()).empName(employee.getEmpName()).empEmail(employee.getEmpEmail())
                .empPhone(employee.getEmpPhone()).empMgr(employee.getEmpMgr()).empPassword(employee.getEmpPassword()).empPosition(employee.getEmpPosition())
                .empHireDate(employee.getEmpHireDate()).empLeader(employee.getEmpLeader()).deptNo(employee.getDeptNo()).empAnnual(employee.getEmpAnnual()).lastAnnualUpdateDate(employee.getLastAnnualUpdateDate()).build();
        return employeeDto;
    }


}


