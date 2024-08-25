package edu.hillel.SpringBootHillelHW.repo;

import edu.hillel.SpringBootHillelHW.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder()
                .id(rs.getLong("id"))
                .date(((Timestamp) rs.getObject("date")).toLocalDateTime())
                .cost(rs.getDouble("cost"))
                .build();
    }
}
