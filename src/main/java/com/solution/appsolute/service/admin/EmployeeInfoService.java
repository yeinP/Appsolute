package com.solution.appsolute.service.admin;


import com.solution.appsolute.dao.admin.repository.AdminEmployee;
import com.solution.appsolute.dao.admin.repository.AdminEmployeeInfoRepository;
import com.solution.appsolute.dto.admin.EmployeeRequest;
import com.solution.appsolute.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeInfoService {

    @Autowired
    AdminEmployee adminEmployee;

    @Autowired
    AdminEmployeeInfoRepository adminEmployeeInfoRepository;

    //회원 가입
    @Transactional
    public void registerEmployee(String empName, String empEmail, String empPhone, String empPassword, Long deptNo, String empPosition, int empLeader, int empMgr) {
        Employee employee = new Employee();
        employee.setEmpName(empName);
        employee.setEmpEmail(empEmail);
        employee.setEmpPhone(empPhone);
        employee.setEmpPassword(empPassword);
        employee.setDeptNo(deptNo);
        employee.setEmpHireDate(LocalDateTime.now());
        employee.setEmpPosition(empPosition);
        employee.setEmpLeader(empLeader);
        employee.setEmpMgr(empMgr);
        adminEmployee.insert(employee);
    }

    //사원 정보 수정 시 사용
    public EmployeeRequest employeeInfoModifyView(Long empNum) {
        Optional<Employee> empInfo = adminEmployeeInfoRepository.findByEmpNum((long) empNum);
        Employee employee = empInfo.get();
        EmployeeRequest updateRequest = new EmployeeRequest(employee.getEmpNum(), employee.getEmpName(), employee.getDeptNo(), employee.getEmpPhone(), employee.getEmpEmail(),
                employee.getEmpPassword(), employee.getEmpHireDate(), employee.getEmpPosition(), employee.getEmpLeader(), employee.getEmpMgr());
        return updateRequest;
    }




}
