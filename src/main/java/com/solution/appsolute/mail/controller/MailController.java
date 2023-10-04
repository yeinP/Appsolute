package com.solution.appsolute.mail.controller;


import com.solution.appsolute.admin.dao.repository.AdminEmployee;
import com.solution.appsolute.admin.dao.repository.AdminEmployeeRepository;
import com.solution.appsolute.login.dto.AuthInfo;
import com.solution.appsolute.mail.dto.*;
import com.solution.appsolute.mail.dao.repository.MailRepository;

import com.solution.appsolute.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class MailController {

//    @PersistenceContext
//    EntityManager em;

    @Autowired
    MailService mailService;

    @Autowired
    MailRepository mailRepository;

    @Autowired
    AdminEmployee adminEmployee;

    @Autowired
    AdminEmployeeRepository adminEmployeeRepository;

    @GetMapping("/test")
    public String test(Model model){
//        model.addAttribute("list", mailDao.listDao(1L, 1L));
        model.addAttribute("list", mailRepository.list(1L, 1L));
        return "list";
    }

    @GetMapping("/mail") // 전체 메일 읽기
    public String list(Model model, @RequestParam(value="pageNo", required = false) String pageNoVal,
                       HttpSession session){  //김승석 - 세션 추가
        int pageNo = 1;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }
        // 세션 부분
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        MailPage mailpage = mailService.getMailPage(authInfo.getEmp_num(), pageNo);
        model.addAttribute("list", mailpage); // 세션 아이디값으로 받기 (나중에 service로 추가해야 함!)
        return "mail/mailList";
    }

    @GetMapping("/mail/send") // 보낸 메일 읽기
    public String mailSender(Model model,HttpSession session, @RequestParam(value="pageNo", required = false) String pageNoVal){
        int pageNo = 0;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }
        Pageable pageable = PageRequest.of(pageNo, 10);
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        Page<MailDto> list = mailRepository.findByMailSend(authInfo.getEmp_num(), pageable);

        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list); // JPA(MailRepository)
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "mail/mailSend";
    }

    @GetMapping("/mail/receive") // 받은 메일 읽기
    public String mailReceiver(Model model,HttpSession session, @RequestParam(value="pageNo", required = false) String pageNoVal){
        int pageNo = 0;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }
        Pageable pageable = PageRequest.of(pageNo, 10);
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        Page<MailDto> list = mailRepository.findByMailReceive(authInfo.getEmp_num(), pageable);

        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list); //jpa(MailRepository)
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "mail/mailReceive";
    }

    @GetMapping("/mail/unread") // 안 읽은 메일
    public String mailUnread(Model model, HttpSession session, @RequestParam(value="pageNo", required = false) String pageNoVal){
        int pageNo = 1;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        MailPage mailpage = mailService.getUnreadMailPage(authInfo.getEmp_num(), pageNo);
        model.addAttribute("list", mailpage); // 세션 아이디값으로 받기 (나중에 service로 추가해야 함!)
        System.out.println("===========================" + mailpage);
        return "mail/mailUnread";
    }

    @GetMapping("/mail/read/{mailNum}") //해당 메일 읽기
    public String readMail(HttpServletRequest req, Model model, @PathVariable Long mailNum, HttpSession session){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        try{
            MailListRequest request = mailService.getMail(authInfo.getEmp_num(), mailNum, true);
            model.addAttribute("list", request);
            return "mail/mailRead";
        } catch (MailNotFoundException e){
            return "mail/errorPage";
        }
    }

    @GetMapping("/mail/write")
    public String writeGet(Model model){
        model.addAttribute("deptNameList", adminEmployee.getDeptNoAndDeptName());
        return "mail/mailForm";
    }

    @ResponseBody
    @GetMapping(value = "/mailSender")
    public List<Object[]> mailReceiver(@RequestParam("deptNo") Long deptNo) {
        List<Object[]> getEmpInfo = adminEmployeeRepository.findEmployeesByDeptNo(deptNo);
        return getEmpInfo;
    }

    @ResponseBody
    @PostMapping(value = "/mail/write")
    public String mailPost(writeMailDto writeMailDto, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        mailService.mailSend(authInfo.getEmp_num(), writeMailDto);
        return "redirect:/mail/mailList";
    }

    @GetMapping("/mail/delete/{no}")
    public String mailDelete(Model model, @PathVariable Long no ){
        mailRepository.deleteById(no);
        return"redirect:/mail";
    }
}
