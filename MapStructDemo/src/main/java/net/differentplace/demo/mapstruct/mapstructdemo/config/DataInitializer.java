package net.differentplace.demo.mapstruct.mapstructdemo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Category;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Product;
import net.differentplace.demo.mapstruct.mapstructdemo.repository.CategoryRepository;
import net.differentplace.demo.mapstruct.mapstructdemo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        log.info("Initializing sample data...");

        // Create categories
        Category electronics = new Category();
        electronics.setName("Electronics");
        electronics.setDescription("Electronic devices and accessories");
        categoryRepository.save(electronics);

        Category books = new Category();
        books.setName("Books");
        books.setDescription("Physical and digital books");
        categoryRepository.save(books);

        Category clothing = new Category();
        clothing.setName("Clothing");
        clothing.setDescription("Apparel and fashion items");
        categoryRepository.save(clothing);

        // Create products
        Product laptop = new Product();
        laptop.setName("MacBook Pro");
        laptop.setDescription("Apple MacBook Pro 14-inch with M3 chip");
        laptop.setPrice(new BigDecimal("1999.99"));
        laptop.setStockQuantity(50);
        laptop.setCategory(electronics);
        laptop.setCreatedAt(LocalDateTime.now());
        productRepository.save(laptop);

        Product smartphone = new Product();
        smartphone.setName("iPhone 15 Pro");
        smartphone.setDescription("Apple iPhone 15 Pro 256GB");
        smartphone.setPrice(new BigDecimal("1199.99"));
        smartphone.setStockQuantity(100);
        smartphone.setCategory(electronics);
        smartphone.setCreatedAt(LocalDateTime.now());
        productRepository.save(smartphone);

        Product headphones = new Product();
        headphones.setName("AirPods Pro");
        headphones.setDescription("Apple AirPods Pro 2nd generation");
        headphones.setPrice(new BigDecimal("249.99"));
        headphones.setStockQuantity(200);
        headphones.setCategory(electronics);
        headphones.setCreatedAt(LocalDateTime.now());
        productRepository.save(headphones);

        Product javaBook = new Product();
        javaBook.setName("Effective Java");
        javaBook.setDescription("Effective Java by Joshua Bloch, 3rd Edition");
        javaBook.setPrice(new BigDecimal("45.99"));
        javaBook.setStockQuantity(75);
        javaBook.setCategory(books);
        javaBook.setCreatedAt(LocalDateTime.now());
        productRepository.save(javaBook);

        Product springBook = new Product();
        springBook.setName("Spring in Action");
        springBook.setDescription("Spring in Action, 6th Edition");
        springBook.setPrice(new BigDecimal("49.99"));
        springBook.setStockQuantity(60);
        springBook.setCategory(books);
        springBook.setCreatedAt(LocalDateTime.now());
        productRepository.save(springBook);

        Product tshirt = new Product();
        tshirt.setName("Developer T-Shirt");
        tshirt.setDescription("100% cotton t-shirt with coding print");
        tshirt.setPrice(new BigDecimal("29.99"));
        tshirt.setStockQuantity(150);
        tshirt.setCategory(clothing);
        tshirt.setCreatedAt(LocalDateTime.now());
        productRepository.save(tshirt);

        log.info("Sample data initialized: {} categories and {} products created",
                categoryRepository.count(), productRepository.count());
    }
}
