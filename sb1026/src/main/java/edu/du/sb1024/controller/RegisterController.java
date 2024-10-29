package edu.du.sb1024.controller;

import edu.du.sb1024.spring.DuplicateMemberException;
import edu.du.sb1024.spring.MemberRegisterService;
import edu.du.sb1024.spring.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

	@Autowired
	private MemberRegisterService memberRegisterService;

	@GetMapping("/register")
	public String root() {
		return "redirect:/register/step1";
	}

	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}

	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if (!agree) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}

	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}

//	@PostMapping("/register/step3")
//	public String handleStep3(RegisterRequest regReq) {
//		try {
//			memberRegisterService.regist(regReq);
//			return "register/step3";
//		} catch (DuplicateMemberException ex) {
//			return "register/step2";
//		}
//	}
@PostMapping("/register/step3")
public String handleStep3(@Validated RegisterRequest regReq, Errors errors) {
//	new RegisterRequestValidator().validate(regReq, errors);
	if (errors.hasErrors())
		return "register/step2";

	try {
		memberRegisterService.regist(regReq);
		return "register/step3";
	} catch (DuplicateMemberException ex) {
		errors.rejectValue("email", "duplicate");
//		errors.reject("notMatchingPassword");
		return "register/step2";
	}
}

	// 특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RegisterRequestValidator());
	}

}
