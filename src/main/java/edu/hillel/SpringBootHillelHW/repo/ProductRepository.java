package edu.hillel.SpringBootHillelHW.repo;

import edu.hillel.SpringBootHillelHW.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
