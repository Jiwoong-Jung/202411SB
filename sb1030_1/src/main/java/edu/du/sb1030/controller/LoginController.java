package edu.du.sb1030.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.du.sb1030.spring.AuthInfo;
import edu.du.sb1030.spring.AuthService;
import edu.du.sb1030.spring.Member;
import edu.du.sb1030.spring.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@SessionAttributes("authInfo")
public class LoginController {

    @Autowired
    private AuthService authService;

    @ModelAttribute("authInfo")

    public AuthInfo setUpUserForm(Long id, String email, String password) {

        return new AuthInfo(id, email, password);

    }

    @GetMapping
    public String form(LoginCommand loginCommand) {
        System.out.println("-----------------여기");
    	return "/login/loginForm";
    }

    @PostMapping
    public String submit(LoginCommand loginCommand, Errors errors, HttpSession session) {

        new LoginCommandValidator().validate(loginCommand, errors);
        if (errors.hasErrors()) {
            return "/login/loginForm";
        }
        try {
            AuthInfo authInfo = authService.authenticate(
                    loginCommand.getEmail(),
                    loginCommand.getPassword());

            setUpUserForm(1000L, loginCommand.getEmail(), loginCommand.getPassword());
//            session.setAttribute("authInfo", authInfo);
            System.out.println(authInfo.getName() + " 세션 저장!");
            return "/login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "/login/loginForm";
        }
    }

    @GetMapping("/main")
    public String main2() {
        return "/login/main";
    }

}
