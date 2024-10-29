package com.example.demo.controller;

import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final NoticeRepository noticeRepository;
    @GetMapping("/")
    public String root() {
        return "view/index";
    }

    @GetMapping("/page2")
    public String listAction(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        //List<Notice> list = noticeRepository.findAll();
        List<Notice> list = noticeRepository.findAllByOrderBySeqDesc();
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<Notice> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        model.addAttribute("list", page);
        return "view/list";
    }

    @GetMapping("/detail")
    public String detail(Long seq, Model model) {
        Notice notice = noticeRepository
                .findById(seq).orElseThrow();
        model.addAttribute("notice", notice);
        return "view/detail";
    }

    @PostMapping("/addNotice")
    @ResponseBody
    public void addNotice(Notice notice) {
        noticeRepository.insertNotice(notice);;
    }

    @PostMapping("/editNotice")
    @ResponseBody
    public String editNotice(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("seq") Long seq) {
        try {
            Notice notice = noticeRepository.findById(seq).orElseThrow();
            if (notice != null) {
                notice.setTitle(title);
                notice.setContent(content);
                noticeRepository.save(notice);

                return "수정되었습니다.";
            } else {
                return "해당 공지를 찾을 수 없습니다.";
            }
        } catch (Exception e) {
            return "수정 실패!";
        }
    }

    @PostMapping("/deleteNotice")
    @ResponseBody
    public String deleteNotice(@RequestParam("seq") Long seq) {
        try {
            Notice notice = noticeRepository.findById(seq).orElseThrow();
            if (notice != null) {
                noticeRepository.delete(notice);
                return "삭제되었습니다.";
            } else {
                return "해당 공지를 찾을 수 없습니다.";
            }
        } catch (Exception e) {
            return "삭제 실패!";
        }
    }

}
