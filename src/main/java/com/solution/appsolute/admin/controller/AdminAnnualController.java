package com.solution.appsolute.admin.controller;


import com.solution.appsolute.admin.dao.repository.AdminEmployeeRepository;
import com.solution.appsolute.admin.dto.EmpAnnualDto;
import com.solution.appsolute.admin.dto.EmployeeDto;
import com.solution.appsolute.admin.dto.PageRequestDto;
import com.solution.appsolute.admin.service.AdminEmployeeService;
import com.solution.appsolute.entity.Board;
import com.solution.appsolute.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.solution.appsolute.login.dto.AuthInfo;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/annual")
public class AdminAnnualController {


    @Autowired
    AdminEmployeeService adminEmployeeService;

    @Autowired
    AdminEmployeeRepository adminEmployeeRepository;

//    @GetMapping("all")
//    public String allAnnualList(@RequestParam(value="pageNo", required = false) String pageNoVal, Model model){
//        int pageNo = 1;
//        if (pageNoVal != null){
//            pageNo = Integer.parseInt(pageNoVal);
//        }
//        model.addAttribute("annualList", adminEmployeeService.getAllEmpAnnualList(pageNo));
//        return "/admin/annual/all";
//    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String allAnnualList(@PageableDefault Pageable pageable, PageRequestDto pageRequestDto, Model model, HttpSession session) {
        if ("empHireDate".equals(pageRequestDto.getType())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(pageRequestDto.getKeyword(), formatter);
            pageRequestDto.setKeyword(localDate.format(formatter));
        }

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        model.addAttribute("annualList", adminEmployeeService.getEmployeeList(pageRequestDto));
        return "/admin/annual/all";
    }

    @RequestMapping(value = "/getEmployeeInfo/{empNum}", method = RequestMethod.GET)
    public String employeeInfo(@PathVariable long empNum, Model model, HttpSession session) {
        EmployeeDto employeeDto = adminEmployeeService.readEmployee(empNum);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        model.addAttribute("employeeInfo", employeeDto);
        return "admin/annual/getEmployeeInfo";
    }

    @PostMapping("/getModifyAnnual")
    @Transactional
    public String employeeAnnualModift(EmployeeDto employeeDto, RedirectAttributes redirectAttributes){
        adminEmployeeService.modifyEmpAnnual(employeeDto);
       redirectAttributes.addAttribute("empNum", employeeDto.getEmpNum());
        return "redirect:/admin/annual/getEmployeeInfo/" + employeeDto.getEmpNum();
    }


    @GetMapping("/annualList")
    public String annualList(@RequestParam(value="pageNo", required = false) String pageNoVal, Model model, HttpSession session) {
        int pageNo = 1;
        if (pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("userName", authInfo.getEmp_name());

        model.addAttribute("annualList", adminEmployeeService.getEmpAnnualList(pageNo));
        return "/admin/annual/annualList";
    }

    @ResponseBody
    @PostMapping("/annualUpdate")
    public void annualUpdate(@RequestBody List<Long> empNums) {
        adminEmployeeService.updateEmpAnnualByEmpNums(empNums);
    }

}
