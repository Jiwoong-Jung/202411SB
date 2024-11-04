package edu.du.sb1030.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.du.sb1030.spring.AuthInfo;
import edu.du.sb1030.spring.AuthService;
import edu.du.sb1030.spring.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String form(LoginCommand loginCommand, @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
        System.out.println("-----------------여기");
        if (rCookie != null) {
            loginCommand.setEmail(rCookie.getValue());
            loginCommand.setRememberEmail(true);
        }
    	return "/login/loginForm";
    }

    @PostMapping
    public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {

        new LoginCommandValidator().validate(loginCommand, errors);
        if (errors.hasErrors()) {
            return "/login/loginForm";
        }
        try {
            AuthInfo authInfo = authService.authenticate(
                    loginCommand.getEmail(),
                    loginCommand.getPassword());
            session.setAttribute("authInfo", authInfo);
            Cookie rememberCookie =
                    new Cookie("REMEMBER", loginCommand.getEmail());
            rememberCookie.setPath("/");
            if (loginCommand.isRememberEmail()) {
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            } else {
                rememberCookie.setMaxAge(0);
            }
            response.addCookie(rememberCookie);
            System.out.println(authInfo.getName() + " 세션 저장!");
            return "/login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "/login/loginForm";
        }
    }
}
