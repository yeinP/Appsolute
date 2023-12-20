package com.solution.appsolute.login.controller;

import com.solution.appsolute.admin.dto.EmployeeDto;
import com.solution.appsolute.admin.service.AdminEmployeeService;
import com.solution.appsolute.login.dto.AuthInfo;
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
@RequestMapping("/employee")
public class ModifyController {

    @Autowired
    AdminEmployeeService adminEmployeeService;


    @RequestMapping(value="/employeeInfoModify/{empNum}", method= RequestMethod.GET)
    public String employeeInfoModify(@PathVariable Long empNum, Model model, HttpSession session) {
        EmployeeDto employeeDto = adminEmployeeService.readEmployee(empNum);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("employeeInfo", employeeDto);
        return "/admin/employee/employeeInfoModify";
    }

    @RequestMapping(value="/employeeInfoModify/{empNum}", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public String employeeInfoModifyPost(EmployeeDto employeeDto, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());
        redirectAttributes.addAttribute("empNum", employeeDto.getEmpNum());
        adminEmployeeService.modifyEmployee(employeeDto);
        return "redirect:/login/mypage";
    }
}