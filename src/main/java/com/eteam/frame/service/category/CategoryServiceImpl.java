package com.eteam.frame.service.category;

import com.eteam.frame.domain.Category;
import com.eteam.frame.persistence.CategoryMapper;
import com.eteam.frame.service.BaseService;
import com.eteam.frame.service.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mway on 14-3-13.
 */
@Service
public class CategoryServiceImpl  extends BaseService implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @Override
    public int doInsertCategory(Category category) throws ServiceException {
        int i = categoryMapper.doInsertCategory(category);
        if(false)
            throw new ServiceException();
        return 0;
    }
}
