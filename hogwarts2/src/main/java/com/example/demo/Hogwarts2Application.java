package com.example.demo;

import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class Hogwarts2Application {

    private final NoticeRepository noticeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Hogwarts2Application.class, args);
    }

    @PostConstruct
    public void init() {
        IntStream.rangeClosed(1, 100).forEach(i->{
            Notice notice = Notice.builder()
                    .title("제목"+i)
                    .content("내용"+i)
                    .build();
            noticeRepository.insertNotice(notice);
        });
//        noticeRepository.saveAndFlush(notice);

    }
}
