package com.solution.appsolute.board.dao;

import com.solution.appsolute.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmpName(String empName);
    List<Employee> findAll();

    Optional<Employee> findByEmpEmail(String empEmail);

}
