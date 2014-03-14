package com.eteam.frame.web.category;

import com.eteam.frame.domain.Category;
import com.eteam.frame.service.category.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mway on 14-3-13.
 */
@Controller
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @RequestMapping("/category/donew")
    @ResponseBody
    public Map doInsert() {
        Map map = new HashMap();
        Category category = new Category();
        category.setCategoryId("1");
        category.setName("123123");
        category.setDescription("你发好");
        try {
            int iRtn = categoryService.doInsertCategory(category);
            map.put("OK", "成功了。");

            List<Category> list = categoryService.getCategoryList();
        } catch (Exception e) {
            map.put("ERROR", "回滚了");
        }
        return map;
    }
}
