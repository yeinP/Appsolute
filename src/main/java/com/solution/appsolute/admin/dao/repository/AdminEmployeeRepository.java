package com.solution.appsolute.admin.dao.repository;

import com.solution.appsolute.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdminEmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {


    @Query("SELECT e.empNum, e.empName FROM Employee e WHERE e.deptNo = :deptNo AND e.empPosition = '팀장'")
    List<Object[]> findTeamLeadersByDept(@Param("deptNo") Long deptNo);

    @Query("SELECT e.empNum, e.empName FROM Employee e WHERE e.deptNo = :deptNo")
    List<Object[]> findMgrByDeptNo(@Param("deptNo") Long deptNo);

    @Query("SELECT e FROM Employee e WHERE e.deptNo = :deptNo")
    Page<Employee> findAllByDeptNo(@Param("deptNo") long deptNo, Pageable pageable);

    @Query("select count(e) from Employee e where e.deptNo =:deptNo")
    Long countEmployeeByDeptNo(@Param("deptNo") Long deptNo);

    @Query("SELECT e.empNum, e.empName FROM Employee e WHERE e.deptNo = :deptNo")
    List<Object[]> findEmployeesByDeptNo(@Param("deptNo") Long deptNo);


    @Transactional
    @Modifying
    @Query(value = "UPDATE employee " +
            "SET last_annual_update_date = CURRENT_DATE, " +
            "emp_annual = " +
            "CASE  " +
            "    WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 365 THEN emp_annual + 1 " +
            "    WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) >= 365 THEN " +
            "        CASE " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 730 THEN 15 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 1095 THEN 16 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 1460 THEN 17 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 1825 THEN 18 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 2190 THEN 19 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 2555 THEN 20 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 2920 THEN 21 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 3285 THEN 22 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 3650 THEN 23 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 4015 THEN 24 " +
            "            WHEN TIMESTAMPDIFF(DAY, emp_hire_date, CURRENT_DATE) < 4380 THEN 25 " +
            "            ELSE 26 " +
            "        END " +
            "    ELSE emp_annual " +
            "END " +
            "WHERE (last_annual_update_date != CURRENT_DATE OR last_annual_update_date IS NULL) AND emp_num = :emp_num", nativeQuery = true)
    public void updateEmployeeAnnual(@Param("emp_num") Long emp_num);


    Page<Employee> findAllByOrderByEmpNumDesc(Pageable pageable);


}
