package com.solution.appsolute.dao.admin.repository;

import com.solution.appsolute.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface AdminEmployeeInfoRepository extends JpaRepository<Employee, Long>{

        //사원 리스트
        @Query("select e from Employee e order by e.empHireDate desc, e.empName desc ")
        List<Employee> getEmployList();

        //사원 정보
        Optional<Employee> findByEmpNum(Long empNum);


        @Query("SELECT e.empNum, e.empName FROM Employee e WHERE e.deptNo = :deptNo AND e.empPosition = '팀장'")
        List<Object[]> findTeamLeadersByDept(@Param("deptNo") Long deptNo);

        @Query("SELECT e.empNum, e.empName FROM Employee e WHERE e.deptNo = :deptNo")
        List<Object[]> findMgrByDeptNo(@Param("deptNo") Long deptNo);


    }
