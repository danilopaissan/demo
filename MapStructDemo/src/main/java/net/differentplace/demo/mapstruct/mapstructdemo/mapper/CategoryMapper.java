package net.differentplace.demo.mapstruct.mapstructdemo.mapper;

import net.differentplace.demo.mapstruct.mapstructdemo.dto.CategoryDto;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryDto dto);

    List<CategoryDto> toDtoList(List<Category> categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntityFromDto(CategoryDto dto, @MappingTarget Category category);
}
