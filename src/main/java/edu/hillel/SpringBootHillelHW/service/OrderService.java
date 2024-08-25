package edu.hillel.SpringBootHillelHW.service;

import edu.hillel.SpringBootHillelHW.entity.Order;
import edu.hillel.SpringBootHillelHW.entity.Product;
import edu.hillel.SpringBootHillelHW.repo.OrderRepository;
import edu.hillel.SpringBootHillelHW.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    public void addOrder(List<Long> products) {
        List<Product> allById = productRepository.findAllById(products);
        orderRepository.addOrder(allById);
    }

}
