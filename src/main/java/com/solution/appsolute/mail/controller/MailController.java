package com.solution.appsolute.mail.controller;


import com.solution.appsolute.admin.dao.repository.AdminEmployee;
import com.solution.appsolute.admin.dao.repository.AdminEmployeeRepository;
import com.solution.appsolute.entity.Board;
import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.login.dto.AuthInfo;
import com.solution.appsolute.mail.dao.mapper.MailMapper;
import com.solution.appsolute.mail.dto.*;
import com.solution.appsolute.mail.dao.repository.MailRepository;

import com.solution.appsolute.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    MailMapper mailMapper;

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
    public String list(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable, @RequestParam(value="pageNo", required = false) String pageNoVal,
                       HttpSession session){  //김승석 - 세션 추가

        int pageNo = 1;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        MailPage mailpage = mailService.getMailPage(authInfo.getEmp_num(), pageNo);

        Page<Mail> list = mailRepository.findAllByOrderByMailNumDesc(pageable);

        int startPage = Math.max(1, list.getPageable().getPageNumber() - 1);
        int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 3);
        int totalPage = list.getTotalPages();

        model.addAttribute("mailList", list);
        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("list", mailpage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "mail/mailList";
    }

    @GetMapping("/mail/send") // 보낸 메일 읽기
    public String mailSender(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable ,HttpSession session, @RequestParam(value="pageNo", required = false) String pageNoVal){
        int pageNo = 0;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        Page<MailDto> list = mailRepository.findByMailSend(authInfo.getEmp_num(), pageable);

        Page<Mail> mailList = mailRepository.findAllByOrderByMailNumDesc(pageable);

//        int nowPage = list.getPageable().getPageNumber()+1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        int startPage = Math.max(1, list.getPageable().getPageNumber() - 1);
        int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 3);
        int totalPage = list.getTotalPages();

        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("mailList", mailList);
        model.addAttribute("list", list); // JPA(MailRepository)
//        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "mail/mailSend";
    }

    @GetMapping("/mail/receive") // 받은 메일 읽기
    public String mailReceiver(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable, HttpSession session, @RequestParam(value="pageNo", required = false) String pageNoVal){
        int pageNo = 0;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        Page<MailDto> list = mailRepository.findByMailReceive(authInfo.getEmp_num(), pageable);

        Page<Mail> mailList = mailRepository.findAllByOrderByMailNumDesc(pageable);

//        int nowPage = list.getPageable().getPageNumber()+1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        int startPage = Math.max(1, list.getPageable().getPageNumber() - 1);
        int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 3);
        int totalPage = list.getTotalPages();

        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("mailList", mailList);
        model.addAttribute("list", list); //jpa(MailRepository)
//        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "mail/mailReceive";
    }

    @GetMapping("/mail/unread") // 안 읽은 메일
    public String mailUnread(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable, HttpSession session, @RequestParam(value="pageNo", required = false) String pageNoVal){
        int pageNo = 1;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        MailPage mailpage = mailService.getUnreadMailPage(authInfo.getEmp_num(), pageNo, pageable);

        Page<Mail> mailList = mailRepository.findAllByOrderByMailNumDesc(pageable);

        int startPage = Math.max(1, mailList.getPageable().getPageNumber() - 1);
        int endPage = Math.min(mailList.getTotalPages(), mailList.getPageable().getPageNumber() + 3);
        int totalPage = mailList.getTotalPages();

        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("list", mailpage); // 세션 아이디값으로 받기 (나중에 service로 추가해야 함!)
        model.addAttribute("mailList", mailList);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "mail/mailUnread";
    }

    @GetMapping("/mail/read/{mailNum}") //해당 메일 읽기
    public String readMail(HttpServletRequest req, Model model, @PathVariable Long mailNum, HttpSession session){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        try{

            MailListRequest request = mailService.getMail(authInfo.getEmp_num(), mailNum);

            model.addAttribute("userName", authInfo.getEmp_name());
            model.addAttribute("list", request);
            return "mail/mailRead";
        } catch (MailNotFoundException e){
            return "mail/errorPage";
        }
    }

    @GetMapping("/mail/search/{empNum}")
    public String mailLike(HttpServletRequest req, Model model, @PageableDefault(page = 0, size = 10) Pageable pageable,
                           @RequestParam(value="pageNo", required = false) String pageNoVal, @PathVariable Long empNum, HttpSession session){
        int pageNo = 1;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 김승석 - authInfo에 세션 주입
        String name = mailMapper.findByMailLikeName(authInfo.getEmp_num(), empNum);
        MailPage mailpage = mailService.getUnreadMailPageLikeEmp(authInfo.getEmp_num(), pageNo, empNum);

        Page<Mail> list = mailRepository.findAllByOrderByMailNumDesc(pageable);

        int startPage = Math.max(1, list.getPageable().getPageNumber() - 1);
        int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 3);
        int totalPage = list.getTotalPages();

        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("list", mailpage); // 세션 아이디값으로 받기 (나중에 service로 추가해야 함!)
        model.addAttribute("name", name);
        model.addAttribute("mailList", list);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "mail/mailSearch";

    }

    @GetMapping("/mail/write")
    public String writeGet(Model model, HttpSession session){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("deptNameList", adminEmployee.getDeptNoAndDeptName());
        return "mail/mailForm";
    }

    @ResponseBody
    @GetMapping(value = "/mailSender")
    public List<Object[]> mailReceiver(@RequestParam("deptNo") Long deptNo, Model model, HttpSession session) {
        List<Object[]> getEmpInfo = adminEmployeeRepository.findEmployeesByDeptNo(deptNo);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        return getEmpInfo;
    }


    @PostMapping(value = "/mail/write")
    public String mailPost(writeMailDto writeMailDto,Model model, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        mailService.mailSend(authInfo.getEmp_num(), writeMailDto);

        model.addAttribute("userName", authInfo.getEmp_name());
        return "redirect:/mail";
    }

    @GetMapping("/mail/delete/{no}")
    public String mailDelete(@PathVariable Long no ){
        mailRepository.deleteById(no);
        return"redirect:/mail";
    }

    @GetMapping("/mail/change/{no}")
    public String mailChangeStatus(@PathVariable Long no, Model model, HttpSession session) {
        mailRepository.updateById(no);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        
        String url = "redirect:/mail/read/" + no;
        return url;
    }

    @ResponseBody
    @PostMapping("/mail/change")
    public int mailChange(@RequestParam(value = "num") Long no){
        int result = 0;

        mailRepository.updateById(no);
        result = 1;
        return result;
    }

    @ResponseBody
    @PostMapping("/mail/delete")
    public int mailCheckDel(@RequestParam(value = "chbox[]") List<String> chArr ) {
        int result = 0;
        int num = 0;

        for(String i : chArr){
            num = Integer.parseInt(i);
            mailMapper.deleteById(num);
        }
        result = 1;
        return result;
    }
}
