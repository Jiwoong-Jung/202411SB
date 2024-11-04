package edu.du.sb1030.controller;

import java.util.List;

import edu.du.sb1030.spring.Member;
import edu.du.sb1030.spring.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MemberListController {

	@Autowired
	private MemberDao memberDao;

	@RequestMapping("/members")
	public String list(
			@ModelAttribute("cmd") ListCommand listCommand,
			Errors errors, Model model) {
		log.info(listCommand.toString());
		if (errors.hasErrors()) {
			return "member/memberList";
		}
		if (listCommand.getFrom() != null && listCommand.getTo() != null) {
			List<Member> members = memberDao.selectByRegdate(
					listCommand.getFrom(), listCommand.getTo());
			System.out.println(members);
			model.addAttribute("members", members);
		}
		return "/member/memberList";
	}

}