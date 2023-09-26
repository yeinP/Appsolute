package com.solution.appsolute.dao.admin.repository;

import com.solution.appsolute.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AdminEmployee {
    @PersistenceContext
    EntityManager em;

    public void insert(Employee employee) {em.persist(employee);}


    public List<Object[]> getDeptNoAndDeptName() {
        String jpql = "SELECT d.deptNo, d.DeptName FROM Dept d";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }


}
