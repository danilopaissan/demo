package net.differentplace.demo.mapstruct.mapstructdemo.repository;

import net.differentplace.demo.mapstruct.mapstructdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);
}
