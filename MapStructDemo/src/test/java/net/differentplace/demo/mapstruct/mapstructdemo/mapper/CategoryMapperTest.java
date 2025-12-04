package net.differentplace.demo.mapstruct.mapstructdemo.mapper;

import net.differentplace.demo.mapstruct.mapstructdemo.dto.CategoryDto;
import net.differentplace.demo.mapstruct.mapstructdemo.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void shouldMapCategoryToDto() {
        // given
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setDescription("Electronic devices");

        // when
        CategoryDto dto = categoryMapper.toDto(category);

        // then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Electronics", dto.getName());
        assertEquals("Electronic devices", dto.getDescription());
    }

    @Test
    void shouldMapDtoToCategory() {
        // given
        CategoryDto dto = new CategoryDto();
        dto.setId(1L);
        dto.setName("Books");
        dto.setDescription("Physical and digital books");

        // when
        Category category = categoryMapper.toEntity(dto);

        // then
        assertNotNull(category);
        assertEquals("Books", category.getName());
        assertEquals("Physical and digital books", category.getDescription());
        // products is ignored during mapping but initialized as empty list by entity default
    }

    @Test
    void shouldMapCategoryListToDtoList() {
        // given
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("Electronics");

        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setName("Books");

        List<Category> categories = Arrays.asList(cat1, cat2);

        // when
        List<CategoryDto> dtos = categoryMapper.toDtoList(categories);

        // then
        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals("Electronics", dtos.get(0).getName());
        assertEquals("Books", dtos.get(1).getName());
    }

    @Test
    void shouldUpdateCategoryFromDto() {
        // given
        Category existing = new Category();
        existing.setId(1L);
        existing.setName("Old Name");
        existing.setDescription("Old Description");

        CategoryDto dto = new CategoryDto();
        dto.setId(999L); // should be ignored
        dto.setName("New Name");
        dto.setDescription("New Description");

        // when
        categoryMapper.updateEntityFromDto(dto, existing);

        // then
        assertEquals(1L, existing.getId()); // id should not change
        assertEquals("New Name", existing.getName());
        assertEquals("New Description", existing.getDescription());
    }
}
