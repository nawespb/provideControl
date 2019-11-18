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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            orders = orderRepository.findByNameContainsIgnoreCase(filter);
        } else {
            orders = orderRepository.findAll();
        }
        model.addAttribute("orders", orders);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam (required = true) String name,
                      @RequestParam (required = false, defaultValue = " ") String description,
                      @RequestParam (required = false, defaultValue = "0") Integer count,
                      @RequestParam (required = false, defaultValue = "1900-01-01") String date,
                      Map<String, Object> model) throws ParseException {
        Order orr = new Order(name, description, count, user, date);
        orderRepository.save(orr);
        Iterable<Order> orders = orderRepository.findAll();
        model.put("orders", orders);
        return "main";
    }

}