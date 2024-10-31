package edu.du.sb1030.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
public class CustomEventListener {
    @EventListener
    public void handleContextStart(CustomEvent customEvent) {
        log.info("Handling context started event. message : {}", customEvent.getProductName());
    }
}
