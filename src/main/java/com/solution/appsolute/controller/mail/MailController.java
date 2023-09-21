package com.solution.appsolute.controller.mail;

import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailRepository mailRepository;

    @GetMapping
    public String list(Model model){
        List<Mail> list = mailRepository.getListDesc();
        model.addAttribute("list", list);
        return "mailList";
    }
}
