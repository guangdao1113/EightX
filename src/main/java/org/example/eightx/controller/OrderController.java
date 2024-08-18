package org.example.eightx.controller;

import org.example.eightx.entity.Order;
import org.example.eightx.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


//    @GetMapping("/orders/{year}/{month}/{day}")
//    public List<Order> getDailyOrders(@PathVariable String year, @PathVariable String month, @PathVariable String day) {
//        // Implement filtering by date
//        return orderRepository.findAll().stream()
//                .filter(order -> order.getOrderDate().startsWith(year + "-" + month + "-" + day))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/orders/{year}/{month}")
//    public List<Order> getMonthlyOrders(@PathVariable String year, @PathVariable String month) {
//        // Implement filtering by year and month
//        return orderRepository.findAll().stream()
//                .filter(order -> order.getOrderDate().startsWith(year + "-" + month))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/orders/{year}")
//    public List<Order> getYearlyOrders(@PathVariable String year) {
//        // Implement filtering by year
//        return orderRepository.findAll().stream()
//                .filter(order -> order.getOrderDate().startsWith(year))
//                .collect(Collectors.toList());
//    }
}
