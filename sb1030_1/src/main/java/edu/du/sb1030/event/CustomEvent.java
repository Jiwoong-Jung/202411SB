package edu.du.sb1030.event;

import lombok.*;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
@ToString
public class CustomEvent extends ApplicationEvent {
    private Long id;
    private String productName;
    private int quantity;
    private double price;

    public CustomEvent(Object source, Long id, String productName, int quantity, double price) {
        super(source);
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
