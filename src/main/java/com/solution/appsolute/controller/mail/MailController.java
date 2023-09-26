package com.solution.appsolute.controller.mail;

import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.repository.MailEm;
import com.solution.appsolute.repository.MailRepository;
import com.solution.appsolute.spring.dao.mail.MailDao;
import com.solution.appsolute.spring.dto.MailListRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private MailDao mailDao;

    @GetMapping("/mail")
    public String test(Model model){
//        model.addAttribute("list", mailDao.listDao(1L, 1L));

        model.addAttribute("list", mailRepository.list(1L, 1L));
        return "list";
    }

    @GetMapping("/mail/{id}")
    public String list(Model model, @PathVariable Long id){
        model.addAttribute("list", mailDao.listDao(id));
//        System.out.println("-----------------" + mailRepository.list(id));
        return "mail/mailList";
    }

    @GetMapping("/mail/send/{send}")
    public String mailSender(Model model, @PathVariable Long send){
        model.addAttribute("list", mailRepository.findByMailSend(send)); // JPA(MailRepository)
        return "mail/mailSend";
    }

    @GetMapping("/mail/receive/{receive}")
    public String mailReceiver(Model model, @PathVariable Long receive){
        model.addAttribute("list", mailRepository.findByMailReceive(receive)); //jpa(MailRepository)
        System.out.println("------------" + mailRepository.findByMailReceive(receive));
        return "mail/mailReceive";
    }

    @GetMapping("/mail/read/{mailNum}")
    public String readMail(Model model, @PathVariable Long mailNum){
        model.addAttribute("list", mailRepository.list(1L, mailNum));
        return "mail/mailRead";
    }
}
