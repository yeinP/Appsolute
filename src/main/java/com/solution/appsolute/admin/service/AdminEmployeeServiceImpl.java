package com.solution.appsolute.admin.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.solution.appsolute.admin.dao.repository.AdminDeptRepository;
import com.solution.appsolute.admin.dao.repository.AdminEmployeeRepository;
import com.solution.appsolute.admin.dao.repository.AdminMapper;
import com.solution.appsolute.admin.dto.*;
import com.solution.appsolute.entity.Employee;
import com.solution.appsolute.entity.QEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdminEmployeeServiceImpl implements AdminEmployeeService {

    private final AdminEmployeeRepository adminEmployeeRepository;


    private final AdminMapper adminMapper;

    private final AdminDeptRepository adminDeptRepository;

    //employeeService

    @Override
    public Long registerEmployee(EmployeeDto employeeDto) {
        Employee entity = employeeDtoToEntity(employeeDto);
        adminEmployeeRepository.save(entity);
        return entity.getEmpNum();
    }





    @Override
    public EmployeeDto readEmployee(Long empNum) {
        Optional<Employee> result = adminEmployeeRepository.findById(empNum);
        return result.isPresent() ? entityToemployeeDto(result.get()) : null;
    }

    @Override
    public void removeEmployee(Long empNum) {
        adminEmployeeRepository.deleteById(empNum);
    }

    @Override
    public void modifyEmployee(EmployeeDto employeeDto) {
        Optional<Employee> result = adminEmployeeRepository.findById(employeeDto.getEmpNum());
        if(result.isPresent()) {
            Employee entity = result.get();
            entity.changePassword(employeeDto.getEmpPassword());
            entity.changeEmpName(employeeDto.getEmpName());
            entity.changeEmpPhone(employeeDto.getEmpPhone());
            adminEmployeeRepository.save(entity);
        }
    }

    @Override
    public void modifyEmpAnnual(EmployeeDto employeeDto) {
        Optional<Employee> result = adminEmployeeRepository.findById(employeeDto.getEmpNum());
        if(result.isPresent()) {
            Employee entity = result.get();
            entity.changeEmpAnnual(employeeDto.getEmpAnnual());
            adminEmployeeRepository.save(entity);
        }
    }

    @Override
    public void modifyEmployeeDept(EmployeeDto employeeDto) {
        Optional<Employee> result = adminEmployeeRepository.findById(employeeDto.getEmpNum());
        if(result.isPresent()) {
            Employee entity = result.get();
            entity.changeDeptNo(employeeDto.getDeptNo());
            entity.changeEmpPosition(employeeDto.getEmpPosition());
            entity.changeEmpLeader(employeeDto.getEmpLeader());
            entity.changeEmpMgr(employeeDto.getEmpMgr());
            adminEmployeeRepository.save(entity);
        }
    }



    @Override
    public PageResultDto<EmployeeDto, Employee> getEmployeeList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("empNum").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDto);
        Page<Employee> result = adminEmployeeRepository.findAll(booleanBuilder, pageable);
        Function<Employee, EmployeeDto> fn = (entity -> entityToemployeeDto(entity));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public AnnualPage getEmpAnnualList(int pageNum) {
        int size = 10;
        int total = adminMapper.countAnnualList((pageNum - 1) *size, size);
        List<EmpAnnualDto> content = adminMapper.listEmpAnnual((pageNum -1)*size, size);
        return new AnnualPage(total, pageNum, size, content);
    }

    @Override
    public AnnualPage getAllEmpAnnualList(int pageNum) {
        int size = 10;
        int total = adminMapper.countListAllEmployee((pageNum - 1) *size, size);
        List<EmpAnnualDto> content = adminMapper.listAllEmployee((pageNum -1)*size, size);
        return new AnnualPage(total, pageNum, size, content);
    }


    @Override
    @Transactional
    public void updateEmpAnnualByEmpNums(List<Long> empNums) {
        for(Long empNum : empNums) {
            adminEmployeeRepository.updateEmployeeAnnual(empNum);
        }
    }


    @Override
    public PageResultDto<EmployeeDto, Employee> getEmployeeListByDeptNo(PageRequestDto requestDto, long deptNo) {
        Pageable pageable = requestDto.getPageable(Sort.by("empNum").descending());
        Page<Employee> result = adminEmployeeRepository.findAllByDeptNo(deptNo, pageable);
        Function<Employee, EmployeeDto> fn = (entity -> entityToemployeeDto(entity));
        return new PageResultDto<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        String type = requestDto.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QEmployee qEmployee = QEmployee.employee;
        String keyword = requestDto.getKeyword();
        BooleanExpression booleanExpression = qEmployee.empNum.gt(0L);
        booleanBuilder.and(booleanExpression);
        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        if(!type.equals("all")){
            BooleanBuilder searchBuilder = new BooleanBuilder();
            if(type.contains("empNum")){
                searchBuilder.or(qEmployee.empNum.stringValue().contains(keyword));
            }
            if(type.contains("empName")){
                searchBuilder.or(qEmployee.empName.contains(keyword));
            }
            booleanBuilder.and(searchBuilder);
        }
        return booleanBuilder;
    }

}
