package com.solution.appsolute.admin.service;
import com.solution.appsolute.admin.dto.DeptDto;
import com.solution.appsolute.admin.dto.PageRequestDto;
import com.solution.appsolute.admin.dto.PageResultDto;
import com.solution.appsolute.entity.Dept;

public interface AdminDeptService {

    //Dept
    //Dept등록
    Long registerDept(DeptDto deptDto);


    //Dept페이징처리, 리스트 , 등록
    PageResultDto<DeptDto, Dept> getDeptList(PageRequestDto requestDto);



    default Dept deptDtoToEntity(DeptDto deptDto) {
        Dept entity = Dept.builder().deptNo(deptDto.getDeptNo()).deptName(deptDto.getDeptName()).deptLoc(deptDto.getDeptLoc()).deptBranch(deptDto.getDeptBranch()).build();
        return entity;
    }

    default DeptDto entityToDeptDto(Dept dept) {
        DeptDto deptDto  = DeptDto.builder().deptNo(dept.getDeptNo()).deptName(dept.getDeptName()).deptLoc(dept.getDeptLoc()).deptBranch(dept.getDeptBranch()).build();
        return deptDto;
    }

    DeptDto readDept(Long deptNo);
    void modifyDept(DeptDto deptDto);
    void removeDept(Long deptNo);

//    default EmployeeDto entityToemployeeDto(Employee employee) {
//        EmployeeDto employeeDto  = EmployeeDto.builder().empNum(employee.getEmpNum()).empName(employee.getEmpName()).empEmail(employee.getEmpEmail())
//                .empPhone(employee.getEmpPhone()).empMgr(employee.getEmpMgr()).empPassword(employee.getEmpPassword()).empPosition(employee.getEmpPosition())
//                .empHireDate(employee.getEmpHireDate()).empLeader(employee.getEmpLeader()).deptNo(employee.getDeptNo()).build();
//        return employeeDto;
//    }

}
