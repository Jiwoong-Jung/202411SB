package edu.du.sb1031.event;

import edu.du.sb1031.order.Order;
import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    private Order message;

    //생성자
    public CustomEvent(Object source, Order message) {
        super(source);
        this.message = message;
    }

    public Order getMessage() {
        return message;
    }
}
