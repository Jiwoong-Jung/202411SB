package com.example.demo.controller;

import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("loginUser")
public class BeginCotroller {

    private final NoticeRepository noticeRepository;

    @ModelAttribute("loginUser")
    public LoginUser initializeLoginUser() {
        return new LoginUser("처음 방문", "1111");
    }

    @GetMapping("/")
    public String index(Model model, @PageableDefault(page = 0, size = 6) Pageable pageable, @ModelAttribute("loginUser") LoginUser loginUser) {
        List<Notice> list = noticeRepository.findAllByOrderBySeqDesc();
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<Notice> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        model.addAttribute("list", page);
        log.info("loginUser: {}", loginUser);

        model.addAttribute("loginUser", loginUser);
        return "main";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginUser", new LoginUser());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginUser loginUser, HttpSession session) {
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.info("loginUser 세션 삭제 전: {}", session.getAttribute("loginUser"));
        session.invalidate();
//        log.info("loginUser 세션 삭제 후: {}", session.getAttribute("loginUser"));

        return "redirect:/login";
    }

    @GetMapping("/info/staff")
    public void info() {

    }

    @GetMapping("/notice")
    public String notice() {
        return "notice/notice";
    }

    @GetMapping("/notice/read")
    public String noticeRead(@RequestParam int id, Model model) {
        log.info("id: {}", id);
        return "notice/read";
    }

    @GetMapping("/test")
    public String test() {
        return "test/boardWrite";
    }
}
