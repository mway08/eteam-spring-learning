package com.eteam.frame.persistence;

import com.eteam.frame.domain.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> searchProductList(String keywords);

}
