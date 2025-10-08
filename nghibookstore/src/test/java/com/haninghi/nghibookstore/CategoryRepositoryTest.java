package com.haninghi.nghibookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.haninghi.nghibookstore.domain.Category;
import com.haninghi.nghibookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByNameShouldReturnName() {
        List<Category> categories = categoryRepository.findByName("Novel");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Novel");
    }

    @Test
    public void createNewCategory() {
        Category category = new Category("Programming");
        categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();
        assertThat(category.getName()).isEqualTo("Programming");
    }
}