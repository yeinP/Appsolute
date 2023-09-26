package com.solution.appsolute.controller.admin;


import com.solution.appsolute.dao.admin.repository.AdminEmployee;
import com.solution.appsolute.dao.admin.repository.AdminEmployeeInfoRepository;
import com.solution.appsolute.dto.admin.EmployeeRequest;
import com.solution.appsolute.entity.Employee;
import com.solution.appsolute.service.admin.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin/employee")
public class AdminController {

    @Autowired
    AdminEmployee adminEmployee;

    @Autowired
    AdminEmployeeInfoRepository adminEmployeeInfoRepository;

    @Autowired
    EmployeeInfoService employeeInfoService;

    //관리자 화면
    @GetMapping
    public String adminPage() {
        return "/admin/employee/adminPage";
    }

    //회원가입
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String registerEmployee(Model model) {
        model.addAttribute("deptNameList", adminEmployee.getDeptNoAndDeptName());
        return "/admin/employee/registerEmployee";
    }

    //회원가입 & 부서 변경 시 사용
    @GetMapping("/getTeamLeaders")
    @ResponseBody
    public List<Object[]> getTeamLeaders(@RequestParam("deptNo") Long deptNo) {
        List<Object[]> teamLeaders = adminEmployeeInfoRepository.findTeamLeadersByDept(deptNo);
        return teamLeaders;
    }
    @GetMapping("/getMgr")
    @ResponseBody
    public List<Object[]> getMgr(@RequestParam("deptNo")Long deptNo) {
        List<Object[]> getMgr = adminEmployeeInfoRepository.findMgrByDeptNo(deptNo);
        return getMgr;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerEmployee(@RequestParam("empName") String empName, @RequestParam("empEmail") String empEmail, @RequestParam("empPhone") String empPhone,
                                   @RequestParam("empPassword") String empPassword, @RequestParam("deptNo") Long deptNo, @RequestParam("empPosition") String empPosition,
                                   @RequestParam("empLeader") int empLeader, @RequestParam("empMgr") int empMgr, Model model) {
        employeeInfoService.registerEmployee(empName, empEmail, empPhone, empPassword, deptNo, empPosition, empLeader, empMgr);
        return "redirect:/admin/employee/employeeList";
    }


    //리스트
    @RequestMapping(value="/employeeList", method = RequestMethod.GET)
     public ModelAndView employeeList(ModelAndView mav) {
        Sort sort = Sort.by("empHirdate").descending();
        Pageable pageable = PageRequest.of(0,5, sort);
        Page<Employee> page = adminEmployeeInfoRepository.findAll(pageable);
        mav.addObject("page", page);
        List<Employee> list = adminEmployeeInfoRepository.getEmployList();
        mav.addObject("employeeList", list);


        return mav;
    }






    //사원별 정보
    @RequestMapping(value = "/employeeInfo/{empNum}", method = RequestMethod.GET)
    public String employeeInfo(@ModelAttribute Employee employee, @PathVariable long empNum, Model model) {
        Optional<Employee> empInfo = adminEmployeeInfoRepository.findByEmpNum(empNum);
        model.addAttribute("employeeInfo", empInfo.get());
        return "admin/employee/employeeInfo";
    }


    //사원정보 수정
    @RequestMapping(value="/employeeInfoModify/{empNum}", method=RequestMethod.GET)
    public String employeeInfoModify(@PathVariable Long empNum, Model model) {
        EmployeeRequest employeeUpdateRequest = employeeInfoService.employeeInfoModifyView(empNum);
        model.addAttribute("employeeInfo", employeeUpdateRequest);
        return "admin/employee/employeeInfoModify";
    }

    @RequestMapping(value="/employeeInfoModify/{empNum}", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public String employeeInfoModifyPost(@ModelAttribute EmployeeRequest request, Model model, @PathVariable Long empNum) {
        String updatedName = request.getEmpName();
        String updatedPhone = request.getEmpPhone();
        String updatedEmail = request.getEmpEmail();
        Optional<Employee> updatedEmployee = adminEmployeeInfoRepository.findByEmpNum(empNum);
        Employee employeeInfo =  updatedEmployee.get();
        employeeInfo.setEmpName(updatedName);
        employeeInfo.setEmpPhone(updatedPhone);
        employeeInfo.setEmpEmail(updatedEmail);
        adminEmployeeInfoRepository.save(employeeInfo);
        model.addAttribute("employeeInfo", updatedEmployee.get());
        return "admin/employee/employeeInfo";
    }

    //부서 변경
    @RequestMapping(value="/employeeDeptPositionUpdate/{empNum}", method = RequestMethod.GET)
    public String employeeDeptList(@PathVariable Long empNum, Model model) {
        EmployeeRequest employeeUpdateRequest = employeeInfoService.employeeInfoModifyView(empNum);
        model.addAttribute("employeeInfo", employeeUpdateRequest);
        model.addAttribute("deptNameList", adminEmployee.getDeptNoAndDeptName());
        return "admin/employee/employeeDeptPositionUpdate";
    }

    @RequestMapping(value = "/employeeDeptPositionUpdate/{empNum}", method=RequestMethod.POST)
    @Transactional(readOnly = false)
    public String employeeDeptModify(@ModelAttribute EmployeeRequest request, Model model, @PathVariable Long empNum) {
        Long updatedDeptNo = request.getDeptNo();
        String updateEmpPosition = request.getEmpPosition();
        int updateEmpLeader = request.getEmpLeader();
        int updateEmpMgr = request.getEmpMgr();
        Optional<Employee> updatedEmployee = adminEmployeeInfoRepository.findByEmpNum(empNum);
        Employee employee = updatedEmployee.get();
        employee.setDeptNo(updatedDeptNo);
        employee.setEmpPosition(updateEmpPosition);
        employee.setEmpLeader(updateEmpLeader);
        employee.setEmpMgr(updateEmpMgr);
        adminEmployeeInfoRepository.save(employee);
        model.addAttribute("employeeInfo", updatedEmployee.get());
        return "admin/employee/employeeInfo";

    }

    //사원 삭제(퇴사)
    @RequestMapping(value="/employeeResign", method=RequestMethod.POST)
    public String employeeResign(@RequestParam Long empNum) {
        adminEmployeeInfoRepository.deleteById(empNum);
        return "redirect:/admin/employee/employeeList";
    }









}