package edu.hillel.SpringBootHillelHW.repo;

import edu.hillel.SpringBootHillelHW.controller.OrderController;
import edu.hillel.SpringBootHillelHW.entity.Order;
import edu.hillel.SpringBootHillelHW.entity.Product;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class OrderRepository {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final JdbcTemplate jdbcTemplate;

    public List<Order> getAllOrders() {
        String query = "SELECT * FROM orders";
        List<Order> getAll = jdbcTemplate.query(query, new OrderRowMapper());
        logger.debug("For test that does not show in logs");
        logger.info("Recieved all users from BD");
        return getAll;
    }

    public Order getOrderById(Long id) {
        String query = "SELECT * FROM orders WHERE id=?";
        return jdbcTemplate.queryForObject(query, new OrderRowMapper(), id);
    }

    @Transactional
    public void addOrder(List<Product> products) {
        Double orderCost = products.stream().map(Product::getCost)
                .reduce(0.0, (x, y) -> x + y);
        String createOrder = "INSERT INTO orders (date, cost) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(createOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, LocalDateTime.now());
            ps.setDouble(2, orderCost);
            return ps;
        }, keyHolder);

        long orderId = (long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        for (Product prod : products) {
            String addRowToOrderProductTable = "INSERT INTO order_product (order_id, product_id) VALUES(?, ?)";
            jdbcTemplate.update(addRowToOrderProductTable, orderId, prod.getId());
        }
    }
}
