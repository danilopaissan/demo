package net.differentplace.demo.mapstruct.mapstructdemo.mapper;

import net.differentplace.demo.mapstruct.mapstructdemo.dto.CategoryDto;
import net.differentplace.demo.mapstruct.mapstructdemo.dto.ProductDto;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Category;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void shouldMapProductToDto() {
        // given
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setDescription("Electronic devices");

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("A powerful laptop");
        product.setPrice(new BigDecimal("999.99"));
        product.setStockQuantity(10);
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.now());

        // when
        ProductDto dto = productMapper.toDto(product);

        // then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Laptop", dto.getName());
        assertEquals("A powerful laptop", dto.getDescription());
        assertEquals(new BigDecimal("999.99"), dto.getPrice());
        assertEquals(10, dto.getStockQuantity());
        assertNotNull(dto.getCategory());
        assertEquals("Electronics", dto.getCategory().getName());
    }

    @Test
    void shouldMapDtoToProduct() {
        // given
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("Books");
        categoryDto.setDescription("Physical books");

        ProductDto dto = new ProductDto();
        dto.setId(1L);
        dto.setName("Java Book");
        dto.setDescription("Learn Java");
        dto.setPrice(new BigDecimal("49.99"));
        dto.setStockQuantity(25);
        dto.setCategory(categoryDto);

        // when
        Product product = productMapper.toEntity(dto);

        // then
        assertNotNull(product);
        assertEquals("Java Book", product.getName());
        assertEquals("Learn Java", product.getDescription());
        assertEquals(new BigDecimal("49.99"), product.getPrice());
        assertEquals(25, product.getStockQuantity());
        assertNotNull(product.getCategory());
        assertEquals("Books", product.getCategory().getName());
        assertNull(product.getId()); // id should be ignored
        assertNull(product.getCreatedAt()); // createdAt should be ignored
    }

    @Test
    void shouldMapProductListToDtoList() {
        // given
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Phone");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Tablet");
        product2.setCategory(category);

        List<Product> products = Arrays.asList(product1, product2);

        // when
        List<ProductDto> dtos = productMapper.toDtoList(products);

        // then
        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals("Phone", dtos.get(0).getName());
        assertEquals("Tablet", dtos.get(1).getName());
    }

    @Test
    void shouldUpdateProductFromDto() {
        // given
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        Product existing = new Product();
        existing.setId(1L);
        existing.setName("Old Product");
        existing.setDescription("Old Description");
        existing.setPrice(new BigDecimal("100.00"));
        existing.setStockQuantity(5);
        existing.setCategory(category);
        existing.setCreatedAt(LocalDateTime.of(2023, 1, 1, 0, 0));

        CategoryDto newCategoryDto = new CategoryDto();
        newCategoryDto.setId(2L);
        newCategoryDto.setName("Books");

        ProductDto dto = new ProductDto();
        dto.setId(999L); // should be ignored
        dto.setName("New Product");
        dto.setDescription("New Description");
        dto.setPrice(new BigDecimal("200.00"));
        dto.setStockQuantity(15);
        dto.setCategory(newCategoryDto);

        // when
        productMapper.updateEntityFromDto(dto, existing);

        // then
        assertEquals(1L, existing.getId()); // id should not change
        assertEquals("New Product", existing.getName());
        assertEquals("New Description", existing.getDescription());
        assertEquals(new BigDecimal("200.00"), existing.getPrice());
        assertEquals(15, existing.getStockQuantity());
        assertEquals(LocalDateTime.of(2023, 1, 1, 0, 0), existing.getCreatedAt()); // createdAt should not change
    }

    @Test
    void shouldHandleNullCategory() {
        // given
        Product product = new Product();
        product.setId(1L);
        product.setName("Product without category");
        product.setCategory(null);

        // when
        ProductDto dto = productMapper.toDto(product);

        // then
        assertNotNull(dto);
        assertEquals("Product without category", dto.getName());
        assertNull(dto.getCategory());
    }
}
