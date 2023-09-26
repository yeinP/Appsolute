package com.solution.appsolute.login;

import com.solution.appsolute.dto.login.LoginDto;
import com.solution.appsolute.service.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
        public String getLogin() {

            return "/login/login";
        }

    @PostMapping("login/mypage")
    public String postLogin(@RequestParam String emp_num, @RequestParam String emp_password, @RequestParam(value = "rememberMe", required = false) boolean rememberMe,
                            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        LoginDto loginDto = loginService.selectByEmp_num(Long.valueOf(emp_num));

        if (loginDto != null && loginDto.getEmp_password() != null && loginDto.getEmp_password().equals(emp_password)) {
            // 세션을 가져오거나 새로 생성합니다.
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(7200);
            // 세션에 사용자 정보를 저장합니다.
            session.setAttribute("emp_num", loginDto);
            logger.info("emp_num in session: " + session.getAttribute("emp_num"));
            return "/login/mypage";
        } else {
            return "redirect:/";
        }
    }

}
