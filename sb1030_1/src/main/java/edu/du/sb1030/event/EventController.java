package edu.du.sb1030.event;

import edu.du.sb1030.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final CustomEventPublisher customEventPublisher;

    @GetMapping("/event")
    public void event(Order order) {
        customEventPublisher.doStuffAndPublishAnEvent(order);

    }
}
