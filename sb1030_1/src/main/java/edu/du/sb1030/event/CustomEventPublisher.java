package edu.du.sb1030.event;

import edu.du.sb1030.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent(final Order order) {
        System.out.println("Publishing custom event. ");
        CustomEvent customSpringEvent = new CustomEvent(this, order.getId(), order.getProductName(),order.getQuantity(), order.getPrice());
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
