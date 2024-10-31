package com.example.demo.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final CustomEventPublisher customEventPublisher;

    @GetMapping("/event/{msg}")
    public void event(@PathVariable String msg) {
        customEventPublisher.doStuffAndPublishAnEvent("event test:" + msg);

    }
}
