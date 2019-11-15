package com.nawe.provideControl.controller;

import com.nawe.provideControl.domain.Order;
import com.nawe.provideControl.domain.User;
import com.nawe.provideControl.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Order> orders;
        if (filter != null && !filter.isEmpty()) {
            orders = orderRepository.findByName(filter);
        } else {
            orders = orderRepository.findAll();
        }
        model.addAttribute("orders", orders);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam String description,
                      @RequestParam Integer count,
                      Map<String, Object> model) {
        Order orr = new Order(name, description, count, user);
        orderRepository.save(orr);
        Iterable<Order> orders = orderRepository.findAll();
        model.put("orders", orders);
        return "main";
    }

}