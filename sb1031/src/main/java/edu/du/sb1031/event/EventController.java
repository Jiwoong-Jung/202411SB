package edu.du.sb1031.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final CustomEventPublisher customEventPublisher;

    @GetMapping("/event/{msg}")
    public void event(@PathVariable String msg) {
        log.info(msg);
        customEventPublisher.doStuffAndPublishAnEvent("event test:" + msg);

    }
}
