package com.solution.appsolute.admin.controller;

import com.solution.appsolute.admin.dao.repository.AdminEmployeeRepository;
import com.solution.appsolute.admin.dto.DeptDto;
import com.solution.appsolute.admin.dto.PageRequestDto;
import com.solution.appsolute.admin.service.AdminDeptService;
import com.solution.appsolute.admin.service.AdminEmployeeService;
import com.solution.appsolute.login.dto.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/dept")
public class AdminDeptController {

    @Autowired
    AdminEmployeeService adminEmployeeService;

    @Autowired
    AdminDeptService adminDeptService;

    @Autowired
    AdminEmployeeRepository adminEmployeeRepository;

    //dept목록 보이기
    @RequestMapping(value="/deptList" , method = RequestMethod.GET)
    public void deptList(PageRequestDto pageRequestDto, Model model, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("deptList", adminDeptService.getDeptList(pageRequestDto));

    }

    //dept 등록하기
    @RequestMapping(value = "/deptRegister", method=RequestMethod.GET)
    public void deptRegister(Model model, HttpSession session) {

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

    }

    //redirect로 페이지 처리 이휴에 목록페이지로 이동하기
    @RequestMapping(value = "/deptRegister", method=RequestMethod.POST)
    public String deptRegisterPost(DeptDto deptDto, Model model, HttpSession session) {
        adminDeptService.registerDept(deptDto);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        return "redirect:/admin/dept/deptList";
    }
    @RequestMapping(value="/deptInfo/{deptNo}", method=RequestMethod.GET)
    public String deptInfo(@PathVariable long deptNo, Model model, PageRequestDto pageRequestDto, HttpSession session) {
        DeptDto deptDto = adminDeptService.readDept(deptNo);
        Long count = adminEmployeeRepository.countEmployeeByDeptNo(deptNo);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        model.addAttribute("count", count);
        model.addAttribute("empListInDeptNo", adminEmployeeService.getEmployeeListByDeptNo(pageRequestDto, deptNo));
        model.addAttribute("deptInfo", deptDto);
        return "admin/dept/deptInfo";
    }

    @RequestMapping(value="/deptInfoModify/{deptNo}", method=RequestMethod.GET)
    public String deptInfoModify(@PathVariable Long deptNo, Model model, HttpSession session) {
        DeptDto deptDto = adminDeptService.readDept(deptNo);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        model.addAttribute("deptInfo", deptDto);
        return "admin/dept/deptInfoModify";
    }

    @RequestMapping(value="/deptInfoModify/{deptNo}", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public String deptInfoModifyPost(DeptDto deptDto, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        adminDeptService.modifyDept(deptDto);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        redirectAttributes.addAttribute("deptNo", deptDto.getDeptNo());
        return "redirect:/admin/dept/deptInfo/" + deptDto.getDeptNo();
    }
    @RequestMapping(value="/deleteDept",  method=RequestMethod.POST)
    public String deleteDept(Long deptNo) {
        adminDeptService.removeDept(deptNo);
        return "redirect:/admin/dept/deptList";
    }



}

