package edu.du.sb1031.event;

import edu.du.sb1031.order.Order;
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
        Order order = new Order();
        order.setProductName(msg);
        order.setPrice(100);
        order.setQuantity(1);
        order.setId(100L);
        customEventPublisher.doStuffAndPublishAnEvent(order);

    }
}
