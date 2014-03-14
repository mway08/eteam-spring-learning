package com.eteam.frame.persistence;

import com.eteam.frame.domain.Category;

import java.util.List;

public interface CategoryMapper {

    List<Category> getCategoryList();

    Category getCategory(String categoryId);

    int doInsertCategory(Category category);

}
