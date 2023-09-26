package com.solution.appsolute.controller.mail;

import com.solution.appsolute.dao.mail.repository.MailEm;
import com.solution.appsolute.dao.mail.repository.MailRepository;
import com.solution.appsolute.dao.mail.repository.MailDao;
import com.solution.appsolute.dto.login.LoginDto;
import com.solution.appsolute.dto.mail.MailPage;
import com.solution.appsolute.service.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class MailController {

//    @PersistenceContext
//    EntityManager em;

    @Autowired
    MailService mailService;

    @Autowired
    MailEm mailEm;

    @Autowired
    MailRepository mailRepository;

    @Autowired
    private MailDao mailDao;

    @GetMapping("/test")
    public String test(Model model){
//        model.addAttribute("list", mailDao.listDao(1L, 1L));
        System.out.println("========================" + mailRepository.countBy());
        model.addAttribute("list", mailRepository.list(1L, 1L));
        return "list";
    }

    @GetMapping("/mail")
    public String list(HttpServletRequest req, Model model, @RequestParam(value="pageNo", required = false) String pageNoVal){

        int pageNo = 1;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }
        // 세션 부분
        LoginDto user = (LoginDto) req.getSession(false).getAttribute("emp_num");
        MailPage mailpage = mailService.getMailPage(user.getEmp_num(), pageNo);
        model.addAttribute("list", mailpage); // 세션 아이디값으로 받기 (나중에 service로 추가해야 함!)
        return "mail/mailList";
    }

    @GetMapping("/mail/send")
    public String mailSender(HttpServletRequest req, Model model){
        LoginDto user = (LoginDto) req.getSession(false).getAttribute("emp_num");
        model.addAttribute("list", mailRepository.findByMailSend(user.getEmp_num())); // JPA(MailRepository)
        return "mail/mailSend";
    }

    @GetMapping("/mail/receive")
    public String mailReceiver(HttpServletRequest req, Model model){
        LoginDto user = (LoginDto) req.getSession(false).getAttribute("emp_num");
        model.addAttribute("list", mailRepository.findByMailReceive(user.getEmp_num())); //jpa(MailRepository)
        return "mail/mailReceive";
    }

    @GetMapping("/mail/read/{mailNum}")
    public String readMail(HttpServletRequest req, Model model, @PathVariable Long mailNum){
        LoginDto user = (LoginDto) req.getSession(false).getAttribute("emp_num");
        model.addAttribute("list", mailRepository.list(user.getEmp_num(), mailNum)); // 세션 아이디, 메일 번호 ( 나중에 service로!)
        return "mail/mailRead";
    }
}
