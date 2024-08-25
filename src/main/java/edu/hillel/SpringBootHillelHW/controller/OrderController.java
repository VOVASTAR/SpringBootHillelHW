package edu.hillel.SpringBootHillelHW.controller;


import edu.hillel.SpringBootHillelHW.entity.Order;
import edu.hillel.SpringBootHillelHW.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")

@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get-all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/add-order")
    public void addOrder(@RequestParam("id") List<Long> ids) {
        orderService.addOrder(ids);
    }
}
