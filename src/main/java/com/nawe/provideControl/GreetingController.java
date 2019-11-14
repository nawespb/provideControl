package com.nawe.provideControl;

import com.nawe.provideControl.domain.Order;
import com.nawe.provideControl.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World!") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Order> orders = orderRepository.findAll();
        model.put("orders", orders);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String description, Map<String, Object> model) {
        Order orr = new Order(name, description);
        orderRepository.save(orr);
        Iterable<Order> orders = orderRepository.findAll();
        model.put("orders", orders);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Order> orders;

        if (filter != null && !filter.isEmpty()) {
            orders = orderRepository.findByName(filter);
        } else {
            orders = orderRepository.findAll();
        }

        model.put("orders", orders);

        return "main";
    }

}