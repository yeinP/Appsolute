package com.solution.appsolute.controller.mail;

import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.repository.MailEm;
import com.solution.appsolute.repository.MailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
@Slf4j
public class MailController {

//    @PersistenceContext
//    EntityManager em;

    @Autowired
    MailEm mailEm;

    @Autowired
    MailRepository mailRepository;

    @GetMapping("/mail")
    public String list(Model model){
//        List<Mail> list = mailRepository.getListDesc();
//        model.addAttribute("list", list);
//        TypedQuery<Mail> query = em.createQuery("select m from Mail m", Mail.class);
//
//        List<Mail> resultList = query.getResultList();
//        log.info("==============" + resultList);
//        model.addAttribute("list", resultList);

        model.addAttribute("list", mailEm.findAll());
        return "mail/mailList";
    }
}
