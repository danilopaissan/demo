package net.differentplace.demo.mapstruct.mapstructdemo.repository;

import net.differentplace.demo.mapstruct.mapstructdemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
