package net.differentplace.demo.mapstruct.mapstructdemo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.differentplace.demo.mapstruct.mapstructdemo.dto.ProductDto;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Product;
import net.differentplace.demo.mapstruct.mapstructdemo.mapper.ProductMapper;
import net.differentplace.demo.mapstruct.mapstructdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    public List<ProductDto> findByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return productMapper.toDtoList(products);
    }

    public ProductDto create(ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        product.setCreatedAt(LocalDateTime.now());
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    public ProductDto update(Long id, ProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        productMapper.updateEntityFromDto(dto, existing);
        existing.setUpdatedAt(LocalDateTime.now());

        Product saved = productRepository.save(existing);
        return productMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
