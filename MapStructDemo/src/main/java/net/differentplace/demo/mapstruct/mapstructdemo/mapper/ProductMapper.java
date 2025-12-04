package net.differentplace.demo.mapstruct.mapstructdemo.mapper;

import net.differentplace.demo.mapstruct.mapstructdemo.dto.ProductDto;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductDto dto);

    List<ProductDto> toDtoList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(ProductDto dto, @MappingTarget Product product);
}
