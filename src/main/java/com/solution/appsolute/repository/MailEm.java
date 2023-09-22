package com.solution.appsolute.repository;

import com.solution.appsolute.entity.Mail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MailEm {

    @PersistenceContext
    EntityManager em;

    public List<Mail> findAll(){
        String sql = "select m from Mail m";
        TypedQuery<Mail> query = em.createQuery(sql, Mail.class);

        List<Mail> list = query.getResultList();

        return list;
    }
}
