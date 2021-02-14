package com.wingoku.gameofthrones.domain.mappers;

import com.wingoku.gameofthrones.data.network.models.CategoryDTO;
import com.wingoku.gameofthrones.domain.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDomainMapper implements DomainMapper<Category, CategoryDTO> {
    @Override
    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getCategoryName(),
                categoryDTO.getType()
        );
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getCategoryName(),
                category.getType()
        );
    }

    @Override
    public List<Category> fromDTO(List<CategoryDTO> categoryDTOS) {
        List<Category> list = new ArrayList<>();

        for(CategoryDTO dto : categoryDTOS) {
            list.add(fromDTO(dto));
        }
        return list;
    }

    @Override
    public List<CategoryDTO> toDTO(List<Category> categories) {
        List<CategoryDTO> list = new ArrayList<>();

        for(Category dto : categories) {
            list.add(toDTO(dto));
        }
        return list;
    }
}
