package com.irhad.restwebshop.Domain.DTOs;

import com.irhad.restwebshop.Domain.Models.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;

    public CategoryDTO(ItemCategory category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public static Set<CategoryDTO> getCategoryDTOSet(Set<ItemCategory> categorySet) {
        Set<CategoryDTO> categoryDTOS = new HashSet<>();
        for (ItemCategory category : categorySet) {
            categoryDTOS.add(new CategoryDTO(category));
        }
        return  categoryDTOS;
    }
}
