package com.nawe.provideControl.controller;

import com.nawe.provideControl.domain.Order;
import com.nawe.provideControl.domain.User;
import com.nawe.provideControl.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private OrderRepository orderRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
                      @RequestParam String name,
                      @RequestParam(required = false, defaultValue = " ") String description,
                      @RequestParam(required = false, defaultValue = "0") Integer count,
                      @RequestParam(required = false, defaultValue = "1900-01-01") String date,
                      @RequestParam("file") MultipartFile file,
                      Map<String, Object> model) throws ParseException, IOException {
        Order orr = new Order(name, description, count, user, date);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFileName = UUID.randomUUID().toString();
            String finalFileName = uuidFileName + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + finalFileName));
            orr.setFilename(finalFileName);
        }
        orderRepository.save(orr);
        Iterable<Order> orders = orderRepository.findAll();
        model.put("orders", orders);
        return "main";
    }

}