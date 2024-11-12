package com.example.demo.notice.service;

import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository noticeRepository;
    @Override
    public Notice saveNotice(Notice notice) {
//        Notice notice1 = noticeRepository.save(notice);
        noticeRepository.insertNotice(notice);
        return notice;
    }
}
