package com.solution.appsolute.login.controller;



import com.solution.appsolute.admin.dto.EmployeeDto;
import com.solution.appsolute.admin.service.AdminEmployeeService;
import com.solution.appsolute.login.dto.AuthInfo;
import com.solution.appsolute.login.dto.Employee;
import com.solution.appsolute.login.mapper.LoginMapper;
import com.solution.appsolute.login.service.AuthService;
import com.solution.appsolute.login.service.LoginService;
import com.solution.appsolute.mail.dao.mapper.MailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Autowired
    LoginMapper loginMapper;

    @Autowired
    AuthService authService;

    @Autowired
    LoginService loginService;

    @Autowired
    MailMapper mailMapper;

    @Autowired
    AdminEmployeeService adminEmployeeService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public String getLogin() {

        return "/login/login";
    }


    @GetMapping("/login/mypage")
    public String mypage(HttpSession session, Model model) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("empList", loginMapper.myPageListEmp());
        int mailTotal = mailMapper.countUnreadDao(authInfo.getEmp_num());
        model.addAttribute("mailTotal", mailTotal);
        model.addAttribute("empNum", authInfo.getEmp_num());
        model.addAttribute("userName", authInfo.getEmp_name());
        model.addAttribute("dept_no", authInfo.getDept_no());
        model.addAttribute("emp_phone", authInfo.getEmp_phone());
        model.addAttribute("emp_password", authInfo.getEmp_password());
        model.addAttribute("authInfo", authInfo);
        return "/login/mypage";
    }


    @GetMapping("/login/search")
    public String mypageSearch(HttpSession session, Model model, @RequestParam(value = "keyword") String name){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("empList", loginMapper.myPageListEmpByName(name));
        int mailTotal = mailMapper.countUnreadDao(authInfo.getEmp_num());
        model.addAttribute("mailTotal", mailTotal);
        model.addAttribute("empNum", authInfo.getEmp_num());
        model.addAttribute("userName", authInfo.getEmp_name());
        return "/login/mypage";
    }


    @PostMapping("/login/mypage")
    public String postLogin(@RequestParam Long emp_num, @RequestParam String emp_password,
                            HttpSession session, Model model) {
        AuthInfo authInfo = authService.authenticate(emp_num, emp_password);
        System.out.println("------------------------------" + authInfo);
        session.setAttribute("authInfo", authInfo);
        Employee employee =  loginMapper.selectByEmp_num(emp_num);
        return "redirect:/login/mypage";

    }

    @RequestMapping(value="login/mypage/InfoModify/{empNum}", method= RequestMethod.POST)
    @Transactional(readOnly=false)
    public String employeeInfoModifyPost(EmployeeDto employeeDto, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        model.addAttribute("employeeDto", new EmployeeDto());
        adminEmployeeService.modifyEmployee(employeeDto);
        return "redirect:/login/mypage";
    }




}
