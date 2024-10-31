package edu.du.sb1031.order;

import edu.du.sb1031.event.CustomEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final CustomEventPublisher customEventPublisher;
    private final OrderService orderService;

    @PostMapping
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {

        return orderService.saveOrder(order);
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "/orders/orderList";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/new")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "/orders/orderForm";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute Order order) {
        log.info("Order created: {}", order);
        Order order1 = orderService.saveOrder(order);
        customEventPublisher.doStuffAndPublishAnEvent(order1);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder1(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}