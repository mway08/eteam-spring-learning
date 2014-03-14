package com.eteam.frame.service.category;

import com.eteam.frame.domain.Category;
import com.eteam.frame.service.ServiceException;

import java.util.List;

/**
 * Created by mway on 14-3-13.
 */
public interface CategoryService {
    List<Category> getCategoryList();
    Category getCategory(String categoryId);
    int doInsertCategory(Category category) throws ServiceException;
}
