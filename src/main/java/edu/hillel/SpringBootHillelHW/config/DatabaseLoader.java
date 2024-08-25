package edu.hillel.SpringBootHillelHW.config;

import com.github.javafaker.Faker;
import edu.hillel.SpringBootHillelHW.entity.Product;
import edu.hillel.SpringBootHillelHW.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
//            -------------
//            Faker faker = new Faker();
//            for (int i = 1; i <= 20; i++) {
//                Product product = Product.builder()
//                        .name(faker.food().fruit())
//                        .cost(Math.round(Math.random() * 10000.0) / 100.0)
//                        .build();
//                productRepository.save(product);
//            }
        };
    }
}
